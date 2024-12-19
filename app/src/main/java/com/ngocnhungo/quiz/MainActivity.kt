package com.ngocnhungo.quiz

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val playButton: Button = findViewById(R.id.button)


        playButton.setOnClickListener {

            val intent = Intent(this, QuizActivity::class.java)
            startActivity(intent)
        }
    }
}


