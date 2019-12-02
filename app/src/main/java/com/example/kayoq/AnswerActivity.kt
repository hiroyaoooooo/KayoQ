package com.example.kayoq

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_answer.*
import kotlinx.android.synthetic.main.activity_question.*

//import sun.jvm.hotspot.utilities.IntArray


class AnswerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val day = intent.getIntExtra("Day",99)
        val questionNum = intent.getIntExtra("QuestionNum",99)
        val correctAnswer = intent.getIntExtra("CorrectAnswer",99)

        val database = FirebaseDatabase.getInstance().reference
        val questionRef = database.child("Week").child(day.toString()).child("questionList").child(questionNum.toString())
        val answerRef = database.child("Answer").child(day.toString()).child("userA")

        answerRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val answer = dataSnapshot.getValue(Int::class.java)
                textView.text = answer.toString() + correctAnswer.toString()
                if (answer != null) {
                    if (correctAnswer == answer) {
                        textView.text = "正解🎉"
                    } else {
                        textView.text = "不正解😢"
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("e", "Failed to read value.", error.toException())
            }
        })

        button.setOnClickListener {
            val intent = Intent(this@AnswerActivity, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
        }
    }
}
