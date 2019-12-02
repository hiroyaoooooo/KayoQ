package com.example.kayoq

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_question.*
import kotlinx.android.synthetic.main.activity_waiting_question.*

class QuestionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        val day = intent.getIntExtra("Day",0)
        val questionNum = intent.getIntExtra("QuestionNum",0)

        val database = FirebaseDatabase.getInstance().reference
        val questionRef = database.child("Week").child(day.toString()).child("questionList").child(questionNum.toString())

        questionRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val question = dataSnapshot.getValue(Question::class.java)
                if (question != null) {
                    setUpQuestion(question,day)
                    if (question.state == "result"){
                        val intent = Intent(this@QuestionActivity, AnswerActivity::class.java)
                        intent.putExtra("Day",day)
                        intent.putExtra("QuestionNum", questionNum)
                        intent.putExtra("CorrectAnswer", question.correctAnswer)
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

    fun setUpQuestion(question: Question,day: Int){
        var database: DatabaseReference
        database = FirebaseDatabase.getInstance().reference

        questionTextLabel.text = question.questionText
        if (question.type == "twe") {
            button1.text = question.choiceList[0]
            button2.text = question.choiceList[1]

            button3.visibility = View.INVISIBLE
            button4.visibility = View.INVISIBLE

            button1.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(1)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[0]
            }
            button2.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(2)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[1]
            }
        } else {
            button1.text = question.choiceList[0]
            button2.text = question.choiceList[1]
            button3.text = question.choiceList[2]
            button4.text = question.choiceList[3]
            button3.visibility = View.VISIBLE
            button4.visibility = View.VISIBLE

            button1.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(1)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[0]
            }
            button2.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(2)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[1]
            }
            button3.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(3)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[2]
            }
            button4.setOnClickListener {
                database.child("Answer").child(day.toString()).child("userA").setValue(4)
                yourChoiceLabel.text = "あなたが選んだ答え：" + question.choiceList[3]
            }
        }
    }
}
