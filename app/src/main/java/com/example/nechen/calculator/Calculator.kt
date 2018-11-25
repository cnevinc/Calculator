package com.example.nechen.calculator

const val NOT_A_NUMBER = "Not a number"

open class Calculator {


    interface Listener {
        fun onResultUpdate(r: String)
    }

    var listener: Listener? = null

    enum class State {
        START, X, Y, OP
    }

    enum class OPERATOR {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVIDE,
        EQUAL,
        NONE
    }

    // TODO: We we use string cause we want to save the effort for formatting the display and make input string concat easy.
    // TODO: 1. Use BigDecimal directly here.We don't want to loose the precision when we calculate the money.
    // TODO: 2. Use LiveData and keep the state
    var x = "0"
    var y = "0"
    var op = OPERATOR.NONE
    var state = State.START


    /*
    * State Machine of the calculator.
    * Note: only change the state in this method.
    *
    *
    *
    *                             |<-----(input[=])---- \    |<----------(back to OP STATE)-------------------|
    *                             |                      \   |                                                |
    *                             |                       \  |                                                |
    *                             |                        \ |                                                |
    *                             |                         \|                                                |
    *  Start ---(input[0-9])----> X ---(input[OPERATOR])---> OP ---(input[0-9])---> Y ---(input[OPERATOR])--->|
    *    |                        |                          |                      |
    *   input                   input                      input                  input
    *  OPERATOR                 [0-9]                      [+-*\/]                [0-9]
    *   stays                   stays                      stays                  stays
    *
    *  TODO: Make input a command pattern so no need to check type here.
    *
    * */
    fun input(inputAny: Any) {
        val input = inputAny.toString()
        if (input == OPERATOR.NONE.toString()) {
            x = "0"
            y = "0"
            op = OPERATOR.NONE
            state = State.START
            listener?.onResultUpdate(x)
        }

        when (state) {

            State.START -> {
                if (input.matches(Regex("[0-9]"))) {
                    state = State.X
                    x = input
                    listener?.onResultUpdate(x)
                }
            }
            State.X -> {
                if (input.matches(Regex("[0-9]"))) {
                    x += input
                    listener?.onResultUpdate(x)
                } else {
                    state = State.OP
                    op = OPERATOR.valueOf(input)
                }
            }
            State.Y -> {
                if (input.matches(Regex("[0-9]"))) {
                    y += input
                    listener?.onResultUpdate(y)
                } else {
                    x = eval()
                    listener?.onResultUpdate(x)
                    state = State.OP
                    op = OPERATOR.valueOf(input)
                }
            }
            State.OP -> {
                if (input.matches(Regex("[0-9]"))) {
                    if (op == OPERATOR.EQUAL) {
                        state = State.X
                        x = input
                        listener?.onResultUpdate(x)
                    }else{
                        state = State.Y
                        y = input
                        listener?.onResultUpdate(y)
                    }

                } else {
                    op = OPERATOR.valueOf(input)
                }

            }
        }
    }

    private fun eval(): String {
        val r = when (op) {

            OPERATOR.PLUS -> x.toInt() + y.toInt()
            OPERATOR.MINUS -> x.toInt() - y.toInt()
            OPERATOR.MULTIPLY -> x.toInt() * y.toInt()
            OPERATOR.DIVIDE -> {
                if (y == "0") {
                    return NOT_A_NUMBER
                }
                x.toInt() / y.toInt()
            }
            OPERATOR.NONE -> 0
            OPERATOR.EQUAL -> x.toInt()
        }
        return r.toString()
    }


}
