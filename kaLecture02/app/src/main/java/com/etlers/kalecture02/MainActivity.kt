package com.etlers.kalecture02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGetText.setOnClickListener {
            var resultText = etID.text.toString()
            tvResult.setText(resultText)
        }
    }
}