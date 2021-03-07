package com.etlers.test01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_toast: Button = findViewById(R.id.btnToast)
        btn_toast.setOnClickListener {
            Toast.makeText(this@MainActivity, "You clicked me.", Toast.LENGTH_SHORT).show()
        }
    }
}