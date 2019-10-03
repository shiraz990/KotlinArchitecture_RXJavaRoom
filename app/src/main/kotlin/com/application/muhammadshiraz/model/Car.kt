package com.sevenpeakssoftware.muhammadshiraz.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

/**
 * Class which provides a model for Car
 */
@Entity
data class Car(
        @field:PrimaryKey
        var id: Int =0,
        var title: String="",
        var dateTime: String="",
        var subject: String="",
        var image: String="",
        var description: String=""
)

data class ArticlesResponse(
    val content: List<Content>,
    val serverTime: Int,
    val status: String
)

data class Content(
    val changed: Int,
    val content: List<ContentX>,
    val created: Int,
    val dateTime: String,
    val id: Int,
    val image: String,
    val ingress: String,
    val tags: List<Any>,
    val title: String
)

data class ContentX(
    val description: String,
    val subject: String,
    val type: String
)
