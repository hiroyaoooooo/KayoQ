package com.example.kayoq

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

        button2.setOnClickListener {
            database.child("Week").child("1").child("Date").setValue("11月26日")
            database.child("Week").child("1").child("isOpen").setValue(false)
            database.child("Week").child("2").child("Date").setValue("12月3日")
            database.child("Week").child("2").child("isOpen").setValue(false)
            database.child("Week").child("3").child("Date").setValue("12月10日")
            database.child("Week").child("3").child("isOpen").setValue(false)
            database.child("Week").child("4").child("Date").setValue("12月17日")
            database.child("Week").child("4").child("isOpen").setValue(false)
        }

        val postListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // Get Post object and use the values to update the UI
//                val data = dataSnapshot.getValue()
//                Log.w("Q", "loadPost:onCancelled", data.)
                // ...
            }

            override fun onCancelled(databaseError: DatabaseError) {
                // Getting Post failed, log a message
                Log.w("E", "loadPost:onCancelled", databaseError.toException())
                // ...
            }
        }

        weekRef.addValueEventListener(postListener)

        val dataArray = arrayOf("第1回","第2回","第3回","第4回","第5回")
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataArray)
        listView.adapter = adapter

    }
}
