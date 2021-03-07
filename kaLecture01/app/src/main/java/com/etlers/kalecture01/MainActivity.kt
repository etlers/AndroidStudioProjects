package com.etlers.kalecture01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    // 앱이 최초 실행 시 수행되는 구문
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // xml 파일(화면 뷰)과 연결을 의미함
        setContentView(R.layout.activity_main)

        tvTitle.setText("Hello etlers CHOI!!")
    }
}