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
            test("3", "3")  //  3, none, 0
            test("2", "32")  // 32,none, 0
            test("1", "321")  // 321,none, 0
            test("PLUS", "321")  // 321,plus, 321 <-
            test("PLUS", "321")  // 321,plus, 321
            test("PLUS", "321")  // 321,plus, 321
            test("MINUS", "321")  // 321,minus, 321 <-
            test("2", "2")  // 2,minus, 321
            test("1", "21")  // 21,minus, 321
            test("PLUS", "300")  // 321,div, 300 <-
            test("3", "3")  // 3,div, 300
            test("0", "30")  // 30,div, 300
            test("EQUAL", "330")  // 10,evl, 10  <-
            test("MULTIPLY", "330")  // 10,evl, 10  <-
            test("1", "1")  // 10,evl, 10  <-
            test("0", "10")  // 10,evl, 10  <-
            test("DIVIDE", "3300")  // 10,evl, 10  <-
            test("0", "0")  // 10,evl, 10  <-
            test("EQUAL", NOT_A_NUMBER)  // 10,evl, 10  <-

        }

    }
}