package com.etlers.kalecture03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

// Intent 화면이동 및 데이터 전달
// A 화면에서 B 화면으로 전달
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGoSub.setOnClickListener {
            // var: 변수로 변경 가능.
            // val: 자바의 final. 변경 불가. 선언 이후 수정이 없으면 코틀린에서 사용하라고 권장을 함
            // 다음 화면으로 이동하기 위한 인텐트 객체 생성
            val intent = Intent(this, SubActivity::class.java)
            // tvSendMsg 값을 'msg'라는 키에 담아서 보낸다. 받는 곳에서도 'msg'라는 키로 받아야 함
            intent.putExtra("msg", tvSendMsg.text.toString())
            // 실제 호출
            startActivity(intent)
            // 자기자신 액티비티를 파괴함. 더이상 필요없을 시에 적용. 이후 서브 액티비티에서 뒤로가기 클릭하면 앱이 종료되게 됨
            finish()
        }
    }
}