package com.etlers.testimageview

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnToast.setOnClickListener {
            var num1:Int = Random.nextInt(6) + 1
            var num2:Int = Random.nextInt(6) + 1
            var DiceSum:Int = num1 + num2
            var DurMilliSec:Long = 150

            // 이미지 회전 효과
            ivDice1.animate().apply {
                duration = DurMilliSec
                rotationXBy(360f)
            }
            ivDice2.animate().apply {
                duration = DurMilliSec
                rotationXBy(360f)
            }

            // 임의로 선택된 숫자에 의한 이미지 선택
            var DiceImage1 = when(num1) {
                1->R.drawable.dice1
                2->R.drawable.dice2
                3->R.drawable.dice3
                4->R.drawable.dice4
                5->R.drawable.dice5
                else->R.drawable.dice6
            }
            var DiceImage2 = when(num2) {
                1->R.drawable.dice1
                2->R.drawable.dice2
                3->R.drawable.dice3
                4->R.drawable.dice4
                5->R.drawable.dice5
                else->R.drawable.dice6
            }

            // 선택된 이미지 보여주기
            ivDice1.setImageResource(DiceImage1)
            ivDice2.setImageResource(DiceImage2)
            // 주사위 합게 보여주기
            var MessageVal:String = "합계: " + DiceSum.toString()
            Toast.makeText(this@MainActivity, MessageVal, Toast.LENGTH_SHORT).show()
        }
    }
}