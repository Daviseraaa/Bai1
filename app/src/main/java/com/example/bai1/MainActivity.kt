package com.example.bai1

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editTextNumber: EditText
    private lateinit var radioGroup: RadioGroup
    private lateinit var buttonShow: Button
    private lateinit var listView: ListView
    private lateinit var textViewError: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextNumber = findViewById(R.id.editTextNumber)
        radioGroup = findViewById(R.id.radioGroup)
        buttonShow = findViewById(R.id.buttonShow)
        listView = findViewById(R.id.listView)
        textViewError = findViewById(R.id.textViewError)

        buttonShow.setOnClickListener {
            showNumbers()
        }
    }

    private fun showNumbers() {
        val input = editTextNumber.text.toString()
        textViewError.visibility = View.GONE

        if (input.isEmpty()) {
            textViewError.text = "Please enter a number."
            textViewError.visibility = View.VISIBLE
            return
        }

        val n = input.toIntOrNull()
        if (n == null || n < 0) {
            textViewError.text = "Please enter a positive integer."
            textViewError.visibility = View.VISIBLE
            return
        }

        val resultList = mutableListOf<String>()
        when (radioGroup.checkedRadioButtonId) {
            R.id.radioEven -> {
                for (i in 0..n step 2) {
                    resultList.add(i.toString())
                }
            }
            R.id.radioOdd -> {
                for (i in 1..n step 2) {
                    resultList.add(i.toString())
                }
            }
            R.id.radioPerfectSquare -> {
                var i = 0
                while (i * i <= n) {
                    resultList.add((i * i).toString())
                    i++
                }
            }
            else -> {
                textViewError.text = "Please select a number type."
                textViewError.visibility = View.VISIBLE
                return
            }
        }

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, resultList)
        listView.adapter = adapter
    }
}
