package com.example.nechen.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before

import org.junit.Test
import org.junit.rules.TestRule
import org.junit.Rule


class CalculatorTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    var calculator = Calculator()
    var display = ""

    @Before
    fun warmup() {

        calculator = Calculator()
        display = ""
        calculator.listener = object : Calculator.Listener {
            override fun onResultUpdate(r: String) {
                display = r
            }
        }

    }

    @Test
    fun `consecutive math operation`() {

        calculator.run {

            // first , op , output
            test("3", "3")
            test("2", "32")
            test("1", "321")
            test("PLUS", "321")
            test("PLUS", "321")
            test("PLUS", "321")
            test("MINUS", "321")
            test("2", "2")
            test("1", "21")
            test("PLUS", "300")
            test("3", "3")
            test("0", "30")
            test("EQUAL", "330")
            test("MULTIPLY", "330")
            test("1", "1")
            test("0", "10")
            test("DIVIDE", "3300")
            test("0", "0")
            test(Calculator.OPERATOR.EQUAL, NOT_A_NUMBER)

        }
    }

    @Test
    fun `click equal between equation`() {

        test(3, "3")
        test(2, "32")
        test(Calculator.OPERATOR.EQUAL, "32")
        test(9, "9")
        test(2, "92")
        test(Calculator.OPERATOR.PLUS, "92")
        test(Calculator.OPERATOR.EQUAL, "92")
        test(Calculator.OPERATOR.PLUS, "92")
        test(3, "3")
        test(Calculator.OPERATOR.MINUS, "95")
        test(5, "5")
        test(Calculator.OPERATOR.EQUAL, "90")

    }

    fun test(input: Any, expected: String) {
        calculator.input(input)
        assert(display == expected)
    }
}