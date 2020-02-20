package com.application.muhammadshiraz.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.application.muhammadshiraz.model.Article
import com.application.muhammadshiraz.model.ArticleDao

@Database(entities = [Article::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun ArticleDao(): ArticleDao
}