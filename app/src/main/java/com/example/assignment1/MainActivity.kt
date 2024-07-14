package com.example.assignment1

import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.core.widget.doOnTextChanged

class MainActivity : AppCompatActivity() {
    val questions = listOf(
        "What should you do first thing when you find bug?",
        "What is the best way to fix memory leak?",
        "How should you fix an infinite loop in code?"
    )

    val answers = listOf(
        listOf("Google it", "It is the hardware issue", "Ask a friend", "Restart IDE"),
        listOf("Ignore", "Add more RAM", "Use a profiler", "It's the framework fault"),
        listOf("Add more loggs", "Throw your computer out of the window", "Break it into smaller pieces using BIG hammer..", "Try a different framework/tool")
    )

    val correctAnswers = listOf(
        "Restart IDE",
        "Use a profiler",
        "Break it into smaller pieces using BIG hammer.."
    )

    var currentSelectedAnswerPosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val questionTextView: TextView = findViewById(R.id.questionTextView)
        val resultTextView: TextView = findViewById(R.id.resultTextView)
        val asnSpinner: Spinner = findViewById(R.id.answSpinner)
        val checkAnswerButton: Button = findViewById(R.id.btnCheckAnswer)
        val playAgainButton: Button = findViewById(R.id.btnPlayAgain)

        var currentQuestionIndex = 0

        fun updateQuestion() {
            questionTextView.text = questions[currentQuestionIndex]
            asnSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, answers[currentQuestionIndex])
        }
        updateQuestion()

        asnSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                    currentSelectedAnswerPosition = position
                if (position != null && position > 0) {
                    resultTextView.text = "Are you sure?"
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                resultTextView.text = ""
            }
        }

        checkAnswerButton.setOnClickListener {
            if (currentSelectedAnswerPosition != null) {
                val selectedAnswer = answers[currentQuestionIndex][currentSelectedAnswerPosition]
                if (selectedAnswer == correctAnswers[currentQuestionIndex]) {
                    resultTextView.text = "Correct!"
                } else {
                    resultTextView.text = "Oops! Try again."
                }
            }
        }

        playAgainButton.setOnClickListener {
            currentQuestionIndex = (currentQuestionIndex + 1) % questions.size
            updateQuestion()
            resultTextView.text = ""
        }
    }
}