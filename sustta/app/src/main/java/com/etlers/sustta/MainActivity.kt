package com.etlers.sustta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    lateinit var ivDealer1: ImageView
    lateinit var ivDealer2: ImageView
    lateinit var ivPlayer1: ImageView
    lateinit var ivPlayer2: ImageView

    lateinit var tvPlayaer1: TextView
    lateinit var tvPlayaer2: TextView
    lateinit var tvTitle: TextView

    lateinit var btnDeal: Button

    lateinit var random: Random

    var player1 = 0
    var player2 = 0

    var whatuArray = intArrayOf(
        R.drawable.whatu_128_kernel_01_1,
        R.drawable.whatu_128_kernel_01_2,
        R.drawable.whatu_128_kernel_02_1,
        R.drawable.whatu_128_kernel_02_2,
        R.drawable.whatu_128_kernel_03_1,
        R.drawable.whatu_128_kernel_03_2,
        R.drawable.whatu_128_kernel_04_1,
        R.drawable.whatu_128_kernel_04_2,
        R.drawable.whatu_128_kernel_05_1,
        R.drawable.whatu_128_kernel_05_2,
        R.drawable.whatu_128_kernel_06_1,
        R.drawable.whatu_128_kernel_06_2,
        R.drawable.whatu_128_kernel_07_1,
        R.drawable.whatu_128_kernel_07_2,
        R.drawable.whatu_128_kernel_08_1,
        R.drawable.whatu_128_kernel_08_2,
        R.drawable.whatu_128_kernel_09_1,
        R.drawable.whatu_128_kernel_09_2,
        R.drawable.whatu_128_kernel_10_1,
        R.drawable.whatu_128_kernel_10_2
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
            기본 설정
         */
        random = Random

        tvPlayer.setText("딜러")
        tvDelaer.setText("상대")

        ivDealer1 = findViewById(R.id.ivDealer1)
        ivDealer2 = findViewById(R.id.ivDealer2)
        ivPlayer1 = findViewById(R.id.ivPlayer1)
        ivPlayer2 = findViewById(R.id.ivPlayer2)

        ivDealer1.setImageResource(R.drawable.back_128)
        ivDealer2.setImageResource(R.drawable.back_128)
        ivPlayer1.setImageResource(R.drawable.back_128)
        ivPlayer2.setImageResource(R.drawable.back_128)

        tvPlayaer1 = findViewById(R.id.tvDelaer)
        tvPlayaer2 = findViewById(R.id.tvPlayer)

        btnDeal = findViewById(R.id.btnDeal)

        // 버튼 클릭
        btnDeal.setOnClickListener {
            // 이미지 섞기
            for(i in 0..9 step 1) {
                rotateImage(ivDealer1)
                rotateImage(ivPlayer1)
                rotateImage(ivDealer2)
                rotateImage(ivPlayer2)
            }
            // 각 화투 숫자 가져오기
            var dealerWhatu1 = random.nextInt(whatuArray.size)
            var dealerWhatu2 = random.nextInt(whatuArray.size)
            var playerWhatu1 = random.nextInt(whatuArray.size)
            var playerWhatu2 = random.nextInt(whatuArray.size)
            // 화투가 모두 다른 것이 나올 때까지 수행
            var boolWhatuCheck = false
            while (boolWhatuCheck == false) {
                dealerWhatu1 = random.nextInt(whatuArray.size)
                dealerWhatu2 = random.nextInt(whatuArray.size)
                playerWhatu1 = random.nextInt(whatuArray.size)
                playerWhatu2 = random.nextInt(whatuArray.size)

                if ((dealerWhatu1 != dealerWhatu2) && (playerWhatu1 != playerWhatu2) &&
                    (dealerWhatu1 != playerWhatu1) && (dealerWhatu2 != playerWhatu2) &&
                    (dealerWhatu1 != playerWhatu2) && (dealerWhatu2 != playerWhatu1)) {
                    boolWhatuCheck = true
                }
            }
            // 나온 결과로 화투 이미지 보여주기
            setWhatuImage(dealerWhatu1, ivDealer1)
            setWhatuImage(playerWhatu1, ivPlayer1)
            setWhatuImage(dealerWhatu2, ivDealer2)
            setWhatuImage(playerWhatu2, ivPlayer2)
            // 딜러 기준 정의
            var dealerDdang = false
            var dealerCalc1 = dealerWhatu1 / 2 + 1
            var dealerCalc2 = dealerWhatu2 / 2 + 1
            var dealerSum = dealerCalc1 + dealerCalc2
            // 4, 14이면 삼팔광
            if ((dealerWhatu1 == 4 && dealerWhatu2 == 14) || (dealerWhatu2 == 4 && dealerWhatu1 == 14)) {
                tvDelaer.setText("딜러: 삼팔광땡!!")
                ivDealerResult.setImageResource(R.drawable.winner)
                ivPlayerResult.setImageResource(R.drawable.loser)
            } else if (dealerCalc1 == dealerCalc2) {
                dealerDdang = true
                if (dealerCalc2 == 10) {
                    tvDelaer.setText("상대: 장~~땡!!")
                } else {
                    tvDelaer.setText("딜러: " + dealerCalc2.toString() + " 땡!!")
                }
            } else {
                if (dealerSum > 10) {
                    dealerSum = dealerSum - 10
                }
                tvDelaer.setText("딜러: " + dealerSum.toString() + " 끗.")
            }
            // 상대 기준 정의
            var playerDdang = false
            var playerCalc1 = playerWhatu1 / 2 + 1
            var playerCalc2 = playerWhatu2 / 2 + 1
            var playerSum = playerCalc1 + playerCalc2
            // 4, 14이면 삼팔광
            if ((playerWhatu1 == 4 && playerWhatu2 == 14) || (playerWhatu2 == 4 && playerWhatu1 == 14)) {
                tvPlayer.setText("상대: 삼팔광땡!!")
                ivDealerResult.setImageResource(R.drawable.loser)
                ivPlayerResult.setImageResource(R.drawable.winner)
            } else if (playerCalc1 == playerCalc2) {
                playerDdang = true
                if (playerCalc2 == 10) {
                    tvPlayer.setText("상대: 장~~땡!!")
                } else {
                    tvPlayer.setText("상대: " + playerCalc2.toString() + " 땡!!")
                }
            } else {
                if (playerSum > 10) {
                    playerSum = playerSum - 10
                }
                tvPlayer.setText("상대: " + playerSum.toString() + " 끗.")
            }

            // 땡 여부에 따른 결가 처리
            if (dealerDdang == true && playerDdang == false) {
                ivDealerResult.setImageResource(R.drawable.winner)
                ivPlayerResult.setImageResource(R.drawable.loser)
            } else if (dealerDdang == false && playerDdang == true) {
                ivDealerResult.setImageResource(R.drawable.loser)
                ivPlayerResult.setImageResource(R.drawable.winner)
            } else if (dealerDdang == true && playerDdang == true) {
                if (playerCalc2 > dealerCalc2) {
                    ivDealerResult.setImageResource(R.drawable.loser)
                    ivPlayerResult.setImageResource(R.drawable.winner)
                } else {
                    ivDealerResult.setImageResource(R.drawable.winner)
                    ivPlayerResult.setImageResource(R.drawable.loser)
                }
            } else {
                if (playerSum > dealerSum) {
                    ivDealerResult.setImageResource(R.drawable.loser)
                    ivPlayerResult.setImageResource(R.drawable.winner)
                } else if (playerSum < dealerSum) {
                    ivDealerResult.setImageResource(R.drawable.winner)
                    ivPlayerResult.setImageResource(R.drawable.loser)
                } else {
                    ivDealerResult.setImageResource(R.drawable.timer_1)
                    ivPlayerResult.setImageResource(R.drawable.timer_2)
                }
            }
        }
    }

    // 화투 이미지
    private fun setWhatuImage(number: Int, image: ImageView) {
        image.setImageResource(whatuArray[number])
    }

    // 이미지 회전 효과
    private fun rotateImage(image: ImageView) {
        var DurMilliSec:Long = 150
        image.setImageResource(R.drawable.back_128)
        image.animate().apply {
            duration = DurMilliSec
            rotationXBy(360f)
        }
    }
}