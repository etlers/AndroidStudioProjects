package com.etlers.kalecture03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sub.*

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        if (intent.hasExtra("msg")) {
            //tvGetMsg.text = intent.getStringExtra("msg")
            tvGetMsg.setText(intent.getStringExtra("msg"))
        }
    }
}