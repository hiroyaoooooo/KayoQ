package com.example.kayoq

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Questions(
    val isisOpen:String = "false",
    val state:Int = 0,
    val date:String = "11月11日",
    val questionList: List<Question> = listOf(Question())
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "isisOpen" to isisOpen,
            "date" to date,
            "questionList" to questionList
        )
    }
}