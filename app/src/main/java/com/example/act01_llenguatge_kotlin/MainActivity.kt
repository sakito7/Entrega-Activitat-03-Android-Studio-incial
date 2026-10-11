package com.example.act01_llenguatge_kotlin

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.card.MaterialCardView
import com.google.android.material.slider.Slider

class MainActivity : AppCompatActivity() {
    var home: Boolean = false
    lateinit var card_home: MaterialCardView
    lateinit var card_dona: MaterialCardView


    lateinit var slider: Slider
    lateinit var textSlider: TextView
    var altura: Int = 0


    lateinit var weightText: TextView
    var weightVal : Int = 170
    lateinit var weightAddBtton: Button
    lateinit var weightRemoveBtton: Button


    lateinit var ageText: TextView
    var ageVal : Int = 18

    lateinit var ageAddBtton: Button
    lateinit var ageRemoveBtton: Button


    lateinit var calculateBtn: MaterialCardView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // --------------------------------------------------------- //
        //                        Declarar vars                      //
        // --------------------------------------------------------- //

        card_home = findViewById<MaterialCardView>(R.id.seleccio_home)
        card_dona = findViewById<MaterialCardView>(R.id.seleccio_dona)

        slider = findViewById<Slider>(R.id.slider)
        textSlider = findViewById<TextView>(R.id.textSlider)

        weightText = findViewById<TextView>(R.id.textWeight)
        weightAddBtton = findViewById<Button>(R.id.btnPlusWeight)
        weightRemoveBtton = findViewById<Button>(R.id.btnMinusWeight)

        ageText = findViewById<TextView>(R.id.textAge)
        ageAddBtton = findViewById<Button>(R.id.btnPlusAge)
        ageRemoveBtton = findViewById<Button>(R.id.btnMinusAge)

        calculateBtn = findViewById<MaterialCardView>(R.id.btnCalculate)
        altura = slider.value.toInt()   // comença a 73 el val

        // --------------------------------------------------------- //
        //                            Events                         //
        // --------------------------------------------------------- //


        val normal = getColor(R.color.background_component)
        val selected = Color.parseColor("#6B5BB5")

        card_dona.setOnClickListener {
            card_dona.setCardBackgroundColor(selected)
            card_home.setCardBackgroundColor(normal)
            home = false
        }
        card_home.setOnClickListener {
            card_home.setCardBackgroundColor(selected)
            card_dona.setCardBackgroundColor(normal)
            home = true
        }

        // --------------------------------------------------------- //


        slider.addOnChangeListener { _, value, _ ->
            textSlider.text = value.toInt().toString()
            altura = value.toInt()
        }

        // --------------------------------------------------------- //

        weightAddBtton.setOnClickListener {
            weightVal += 1
            weightText.text = weightVal.toString()
        }

        weightRemoveBtton.setOnClickListener {
            if (weightVal > 1) {
                weightVal -= 1
                weightText.text = weightVal.toString()
            }
        }

        // --------------------------------------------------------- //

        ageAddBtton.setOnClickListener {
            ageVal += 1
            ageText.text = ageVal.toString()
        }

        ageRemoveBtton.setOnClickListener {
            if (ageVal > 1) {
                ageVal -= 1
                ageText.text = ageVal.toString()
            }
        }

        // --------------------------------------------------------- //
        //                          CALCULAR                         //
        // --------------------------------------------------------- //

        calculateBtn.setOnClickListener {
            val bmi = 703f * weightVal / (altura * altura)

            val intent = Intent(this, MainActivity2::class.java)
            intent.putExtra("BMI", bmi)
            startActivity(intent)
        }

    }

}
