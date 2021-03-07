package com.etlers.kalecture04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToast.setOnClickListener {
            ivProfile.setImageResource(R.drawable.android_black)
            Toast.makeText(this@MainActivity, "버튼이 클릭 되었습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}