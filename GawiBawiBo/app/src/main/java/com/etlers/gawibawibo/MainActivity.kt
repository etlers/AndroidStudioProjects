package com.etlers.gawibawibo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var Gawi:ImageView
    lateinit var Bawi:ImageView
    lateinit var Bo:ImageView
    lateinit var Computer:ImageView
    lateinit var Result:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 아이디를 연결해주는 작업
        Gawi = findViewById(R.id.gawi)
        Bawi = findViewById(R.id.bawi)
        Bo = findViewById(R.id.bo)
        Computer = findViewById(R.id.computer)
        Result = findViewById(R.id.result)
        // 이미지를 클릭했을 때 할 작업에 대한 정의
        Gawi.setOnClickListener() {
            RunGame(1, "가위")
        }
        Bawi.setOnClickListener() {
            RunGame(2, "바위")
        }
        Bo.setOnClickListener() {
            RunGame(3, "보")
        }
    }

    private fun RunGame(num:Int, str:String) {
        // 1 ~ 3 까지의 숫자 중에서 임의로 선택하여 컴퓨터의 선택으로 정의
        var ComputerSelected = Random.nextInt(3) + 1
        var ResultText:String
        // 컴퓨터가 선택한 임의의 결과를 이미지로 매핑
        var ComputerImage = when(ComputerSelected) {
            1->R.drawable.gawi
            2->R.drawable.bawi
            else->R.drawable.bo
        }
        // 컴퓨터가 선택한 것
        if (ComputerSelected == 1) {
            ResultText = "가위"
        } else if (ComputerSelected == 2) {
            ResultText = "바위"
        } else {
            ResultText = "보"
        }
        // 최종 결과 출력
        if (ComputerSelected == num) {
            Result.text = "" + str + " vs " + ResultText + "\n비겼습니다."
        } else if ((ComputerSelected == 1 && num == 2) || (ComputerSelected == 2 && num == 3) || (ComputerSelected == 3 && num == 1)) {
            Result.text = "" + str + " vs " + ResultText + "\n이겼습니다."
        } else {
            Result.text = "" + str + " vs " + ResultText + "\n졌습니다."
        }
        // 컴퓨터 선택 이미지 보여주기
        Computer.setImageResource(ComputerImage)
    }
}