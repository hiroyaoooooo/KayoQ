package com.example.kayoq

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance().reference
        val weekRef = FirebaseDatabase.getInstance().getReference("Week")

//        button2.setOnClickListener {
//            val question1 =
//                Question(questionText = "キャシーが憧れている芸能人は秋元梢である。", choiceList = listOf("⭕️", "❌"))
//            val questions = Questions(
//                isisOpen = "false",
//                state = 0,
//                date = "11月10日",
//                questionList = listOf(question1)
//            )
//            database.child("Week").child("1").setValue(questions)
//            database.child("Week").child("2").setValue(questions)
//            database.child("Week").child("3").setValue(questions)
//            database.child("Week").child("4").setValue(questions)
//            database.child("Week").child("5").setValue(questions)
//            database.child("Week").child("6").setValue(questions)
//            database.child("Week").child("7").setValue(questions)
//            database.child("Week").child("8").setValue(questions)
//            database.child("Week").child("9").setValue(questions)
//            database.child("Week").child("10").setValue(questions)
//            database.child("Week").child("11").setValue(questions)
//            database.child("Week").child("12").setValue(questions)
//            database.child("Week").child("13").setValue(questions)
//            database.child("Week").child("14").setValue(questions)
//            database.child("Week").child("15").setValue(questions)
//            database.child("Week").child("16").setValue(questions)
//
//
//        }

        setUpListView()
    }

    fun setUpListView(){
        val dataArray = arrayOf("第1回","第2回","第3回","第4回","第5回","第6回","第7回","第8回","第9回","第10回","第11回","第12回","第13回","第14回","第15回","第16回")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter

        listView.setOnItemClickListener { adapterView, _, position, _ ->
            val times = adapterView.getItemAtPosition(position) as String
            val intent = Intent(this@MainActivity, WaitingQuestion::class.java)
            intent.putExtra("Day", position)
            intent.putExtra("QuestionNum", 0)
            startActivity(intent)
        }
    }
}
