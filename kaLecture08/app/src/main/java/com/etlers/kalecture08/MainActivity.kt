package com.etlers.kalecture08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

/*
    WebView (웹 페이지 실행)
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 자바 스크립트 허용
        wvMain.settings.javaScriptEnabled = true
        /*
            웹뷰에서 새창 방지
         */
        wvMain.webViewClient = WebViewClient()
        wvMain.webChromeClient = WebChromeClient()
        wvMain.loadUrl("https://www.naver.com")
    }

    // 기본은 뒤로가기 버튼이 종료로 되어있음
    override fun onBackPressed() {
        // 웹사이트에서 뒤로 갈 페이지가 존재를 한다면
        if (wvMain.canGoBack()) {
            wvMain.goBack()
        } else {
            super.onBackPressed()
        }
    }
}