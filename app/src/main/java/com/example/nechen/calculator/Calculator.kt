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

    var x = "0"
    var y = "0"
    var op = OPERATOR.NONE
    var state = State.START


    /*
    * State Machine of the calculator.
    * Note: only change the state in this method.
    *
    *  Start ---(input[0-9])----> X ---(input[OPERATOR])---> OP ---(input[0-9])---> Y ---(input[OPERATOR])--->|
    *    |                        |                          |                      |                         |
    *   input                   input                      input                  input                       |
    *  OPERATOR                 [0-9]                      [OPERATOR]             [0-9]                       |
    *   stays                   stays                      stays                  stays                       |
    *                                                        |                                                |
    *                                                        |<----------(back to OP STATE)-------------------|
    *
    * */
    fun input(input: String) {
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
                    state = State.Y
                    y = input
                    listener?.onResultUpdate(y)
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
