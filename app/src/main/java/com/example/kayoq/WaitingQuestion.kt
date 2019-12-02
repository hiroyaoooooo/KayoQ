package com.example.kayoq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_waiting_question.*

class WaitingQuestion : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_waiting_question)

        val day = intent.getIntExtra("Day",0) + 1
        val questionNum = intent.getIntExtra("QuestionNum",0)

        val database = FirebaseDatabase.getInstance().reference
        val questionsRef = database.child("Week").child(day.toString())

        questionsRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val questions = dataSnapshot.getValue(Questions::class.java)
                if (questions != null) {
                    if (questions.isisOpen == "true") {
                        questionsRef.removeEventListener(this)
                        val intent = Intent(this@WaitingQuestion, QuestionActivity::class.java)
                        intent.putExtra("Day",day)
                        intent.putExtra("QuestionNum", questionNum)
                        startActivity(intent)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w("e", "Failed to read value.", error.toException())
            }
        })

    }
}
