package com.etlers.kalecture06

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnNavi.setOnClickListener {
            // START: left, END: right
            layoutDrawer.openDrawer(Gravity.START)
        }
        // 네비게이션 메뉴 아이템 클릭시 속성 부여
        naviView.setNavigationItemSelectedListener(this)
    }

    // 네비게이션 아이템 클릭시 수행
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.access -> Toast.makeText(applicationContext, "접근성", Toast.LENGTH_SHORT).show()
            R.id.email -> Toast.makeText(applicationContext, "이메일", Toast.LENGTH_SHORT).show()
            R.id.message -> Toast.makeText(applicationContext, "에세지", Toast.LENGTH_SHORT).show()
        }
        // 네비게이션 뷰 닫기
        layoutDrawer.closeDrawers()
        return false
    }

    // 뒤로가기 버튼을 눌렀을 경우 처리
    override fun onBackPressed() {
        // 메뉴가 열려있으면 닫아주고
        if (layoutDrawer.isDrawerOpen(Gravity.START)) {
            layoutDrawer.closeDrawers()
        } else {
            // 아니면 그냥 종료한다. finish
            super.onBackPressed() // 일반 뒤로가기 버튼 실행
        }
    }
}