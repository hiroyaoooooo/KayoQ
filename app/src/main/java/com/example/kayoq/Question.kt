package com.example.kayoq

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Question(
    val type:String = "twe",
    val questionText:String = "aaa",
    val correctAnswer:Int = 1,
    val state: String = "question",
    val choiceList: List<String> = listOf("まる","ばつ"))
    {
    
}