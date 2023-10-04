package com.codechallenge2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
    private lateinit var resultTextView: TextView

    private var currentInput = ""
    private var currentOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.inputTextView)
        resultTextView = findViewById(R.id.resultTextView)
    }

    fun onDigitClick(view: View) {
        val digit = (view as Button).text.toString()
        currentInput += digit
        updateInputText()
    }

    fun onOperatorClick(view: View) {
        currentOperator = (view as Button).text.toString()
        currentInput += currentOperator
        updateInputText()
    }

    fun onEqualsClick(view: View) {
        try {
            val result = evaluateExpression()
            currentInput = result.toString()
            currentOperator = ""
            updateInputText()
        } catch (e: Exception) {
            currentInput = "Error"
            currentOperator = ""
            updateInputText()
        }
    }

    fun onClearClick(view: View) {
        currentInput = ""
        currentOperator = ""
        updateInputText()
        resultTextView.text = ""
    }

    private fun updateInputText() {
        inputTextView.text = currentInput
    }

    private fun evaluateExpression(): Double {
        val parts = currentInput.split(currentOperator)
        val operand1 = parts[0].toDouble()
        val operand2 = parts[1].toDouble()

        return when (currentOperator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> {
                if (operand2 == 0.0) {
                    throw ArithmeticException("Division by zero")
                }
                operand1 / operand2
            }
            else -> throw IllegalArgumentException("Invalid operator")
        }
    }
}
