package com.example.nechen.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.display
import kotlinx.android.synthetic.main.activity_main.root

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
        for (i in 0..root.childCount) {
            val child = root.getChildAt(i)
            if (child is Button) {
                child.setOnClickListener(onClickListener)
            }
        }
    }

    object onClickListener : View.OnClickListener {
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.bt_1 -> calculator.input("1")
                R.id.bt_2 -> calculator.input("2")
                R.id.bt_3 -> calculator.input("3")
                R.id.bt_4 -> calculator.input("4")
                R.id.bt_5 -> calculator.input("5")
                R.id.bt_6 -> calculator.input("6")
                R.id.bt_7 -> calculator.input("7")
                R.id.bt_8 -> calculator.input("8")
                R.id.bt_9 -> calculator.input("9")
                R.id.bt_plus -> calculator.input(Calculator.OPERATOR.PLUS.toString())
                R.id.bt_minus -> calculator.input(Calculator.OPERATOR.MINUS.toString())
                R.id.bt_div -> calculator.input(Calculator.OPERATOR.DIVIDE.toString())
                R.id.bt_mul -> calculator.input(Calculator.OPERATOR.MULTIPLY.toString())
                R.id.bt_eq -> calculator.input(Calculator.OPERATOR.EQUAL.toString())


            }
        }
    }


}
