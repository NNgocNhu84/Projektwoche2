package com.ngocnhungo.quiz

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class QuizActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var questionTextView: TextView
    private lateinit var scoreTextView: TextView

    private val questions = listOf(
        Question("What is the capital of France?", listOf("Paris", "London", "Rome", "Berlin"), 0),
        Question("What planet is known as the 'Red Planet'?", listOf("Earth", "Venus", "Mars", "Jupiter"), 2),
        Question("What is 5 + 2?", listOf("6", "7", "8", "9"), 1),
        Question("Which ocean is the largest in the world?", listOf("Atlantic Ocean", "Pacific Ocean", "Indian Ocean", "Arctic Ocean"), 1),
        Question("What is the fastest land animal?", listOf("Lion", "Tiger", "Cheetah", "Horse"), 2),
        Question("Which fruit is known for having seeds on the outside?", listOf("Apple", "Banana", "Strawberry", "Watermelon"), 2),
        Question("Who was the first President of the United States?", listOf("Abraham Lincoln", "George Washington", "Thomas Jefferson", "John Adams"), 1),
        Question("How many players are there on a soccer team on the field?", listOf("9", "10", "11", "12"), 2),
        Question("Who is the famous green ogre from the movie series 'Shrek'?", listOf("Hulk", "Shrek", "Goblin", "Yoda"), 1),
        Question("What is the name of the song that starts with 'Let it go, let it go'?", listOf("Frozen", "Let It Go", "Into the Unknown", "Do You Want to Build a Snowman?"), 1)
    )

    private var currentQuestionIndex = 0
    private var score = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        resultTextView = findViewById(R.id.resultTextView)
        questionTextView = findViewById(R.id.textView2)
        scoreTextView = findViewById(R.id.scoreTextView)
        updateScore()

        displayQuestion()

        val answerButton1: Button = findViewById(R.id.answerButton1)
        val answerButton4: Button = findViewById(R.id.answerButton4)
        val answerButton5: Button = findViewById(R.id.answerButton5)
        val answerButton6: Button = findViewById(R.id.answerButton6)


        answerButton1.setOnClickListener { checkAnswer(0) }
        answerButton4.setOnClickListener { checkAnswer(1) }
        answerButton5.setOnClickListener { checkAnswer(2) }
        answerButton6.setOnClickListener { checkAnswer(3) }
    }


    private fun displayQuestion() {
        val currentQuestion = questions[currentQuestionIndex]
        questionTextView.text = currentQuestion.text


        val buttons = listOf<Button>(
            findViewById(R.id.answerButton1),
            findViewById(R.id.answerButton4),
            findViewById(R.id.answerButton5),
            findViewById(R.id.answerButton6)
        )

        for (i in currentQuestion.answers.indices) {
            buttons[i].text = "${'a' + i}) ${currentQuestion.answers[i]}"
        }
    }

    private fun checkAnswer(answerIndex: Int) {
        val currentQuestion = questions[currentQuestionIndex]

        if (answerIndex == currentQuestion.correctAnswerIndex) {
            score += 10
            scoreTextView.text = "Score: $score"
            resultTextView.text = "You answered correctly! The next question..."
            resultTextView.setTextColor(resources.getColor(android.R.color.holo_green_light))
        } else {
            resultTextView.text = "You answered incorrectly. Try again!"
            resultTextView.setTextColor(resources.getColor(android.R.color.holo_red_light))
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion()
        } else {

            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("SCORE", score)
            startActivity(intent)
            finish()
        }
    }



    private fun updateScore() {
        scoreTextView.text = "Score: $score"
    }
}

data class Question(val text: String, val answers: List<String>, val correctAnswerIndex: Int)
