package com.example.nechen.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import com.example.nechen.calculator.R.id.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        val calculator = Calculator()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculator.listener = object : Calculator.Listener {
            override fun onResultUpdate(r: String) {
                display.text = r
            }
        }


    }
    object onClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                bt_1 -> calculator.input("1")
                bt_2 -> calculator.input("2")
                bt_3 -> calculator.input("3")
                bt_4 -> calculator.input("4")
                bt_5 -> calculator.input("5")
                bt_6 -> calculator.input("6")
                bt_7 -> calculator.input("7")
                bt_8 -> calculator.input("8")
                bt_9 -> calculator.input("9")
                bt_plus -> calculator.input(Calculator.OPERATOR.PLUS.toString())
                bt_minus -> calculator.input(Calculator.OPERATOR.MINUS.toString())
                bt_div -> calculator.input(Calculator.OPERATOR.DIVIDE.toString())
                bt_plus -> calculator.input(Calculator.OPERATOR.PLUS.toString())
                bt_plus -> calculator.input(Calculator.OPERATOR.PLUS.toString())



            }
        }
    }


}
