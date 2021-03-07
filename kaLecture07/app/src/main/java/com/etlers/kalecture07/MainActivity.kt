package com.etlers.kalecture07

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

/*
    Shared Preferences (앱 내부 DB)
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 저장된 데이터 로드
        loadData()
    }

    private fun loadData() {
        val pref = getSharedPreferences("pref", 0)
        // 저장할 당시의 키 값, 키 값이 없는 경우 대체 값
        etMessage.setText(pref.getString("name", ""))
    }

    // 앱이 종료되는 시점에 호출
    override fun onDestroy() {
        super.onDestroy()
        // editText 값 저장
        saveData()
    }

    private fun saveData() {
        val pref = getSharedPreferences("pref", 0)
        // 수정모드
        val edit = pref.edit()
        // 키값, 실제 담아둘 값
        edit.putString("name", etMessage.text.toString())
        // 저장 완료
        edit.apply()
    }
}