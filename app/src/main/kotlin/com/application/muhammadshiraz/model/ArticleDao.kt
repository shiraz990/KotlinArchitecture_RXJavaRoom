package com.application.muhammadshiraz.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface ArticleDao {
    @get:Query("SELECT * FROM Article")
    val all: List<Article>

    @Insert
    fun insertAll(vararg article: Article)
}