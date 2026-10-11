package com.example.act01_llenguatge_kotlin

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import java.util.Locale

class MainActivity2 : AppCompatActivity() {
    lateinit var btnRecalculate: MaterialCardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bmi = intent.getFloatExtra("BMI", 0f)

        val tvCategory = findViewById<TextView>(R.id.tvCategory)
        val tvResult = findViewById<TextView>(R.id.tvResult)
        val tvDescription = findViewById<TextView>(R.id.tvDescription)

        val btnRecalculate = findViewById<MaterialCardView>(R.id.recalculate)

        val category: String
        val description: String
        val color: String

        if (bmi < 18.5f) {
            category = "Underweight"
            description = "Your body weight is lower than normal."
            color = "#3FA9F5"
        } else if (bmi < 25f) {
            category = "Normal"
            description = "You have a normal body weight. Good job!"
            color = "#22D66F"
        } else if (bmi < 30f) {
            category = "Overweight"
            description = "Your body weight is higher than normal."
            color = "#FFB020"
        } else {
            category = "Obese"
            description = "Your body weight is well above normal."
            color = "#FF4D4D"
        }

        tvResult.text = "%.2f".format(Locale.US, bmi)
        tvCategory.text = category
        tvCategory.setTextColor(Color.parseColor(color))
        tvDescription.text = description

        btnRecalculate.setOnClickListener { finish() }
    }
}