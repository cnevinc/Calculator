package com.example.nechen.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import org.junit.Test
import org.junit.rules.TestRule
import org.junit.Rule


class CalculatorTest {
    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()


    @Test
    fun `testInput`() {
        val calculator = Calculator()
        var display = ""
        calculator.listener = object : Calculator.Listener {
            override fun onResultUpdate(r: String) {
                display = r
            }
        }

        calculator.run {
            fun test(input: String, expected: String) {
                input(input)
                assert(display == expected)
            }

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
            test("EQUAL", NOT_A_NUMBER)

        }

    }
}