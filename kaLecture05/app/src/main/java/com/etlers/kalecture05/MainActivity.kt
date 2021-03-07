package com.etlers.kalecture05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var UserList = arrayListOf<User>(
        User(R.drawable.android, "etlers", "49", "Hello"),
        User(R.drawable.android, "team ad", "33", "Hi"),
        User(R.drawable.android, "jason", "23", "Good"),
        User(R.drawable.android, "CHOIS", "45", "OK"),
        User(R.drawable.android, "JY CHOI", "51", "Bye"),
        User(R.drawable.android, "jooyong", "19", "Say")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val items = arrayOf("사과", "배", "딸기", "키위", "이틀러스")
//        // 컨텍스트는 한 액티비티의 모든 정보
//        lvMain.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        // 커스텀 리스트
        val Adapter = UserAdapter(this, UserList)
        lvMain.adapter = Adapter
        lvMain.onItemClickListener = AdapterView.OnItemClickListener {parent, view, position, id ->
            val selectItem = parent.getItemAtPosition(position) as User
            Toast.makeText(this, selectItem.name, Toast.LENGTH_SHORT).show()
        }
    }
}