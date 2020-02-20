package com.application.muhammadshiraz.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.application.muhammadshiraz.model.database.AppDatabase
import com.application.muhammadshiraz.ui.article.ArticleDetailViewModel
import com.application.muhammadshiraz.ui.article.ArticlesListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ArticlesListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "Article").build()
            @Suppress("UNCHECKED_CAST")
            return ArticlesListViewModel(db.ArticleDao()) as T
        }
        if (modelClass.isAssignableFrom(ArticleDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleDetailViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}