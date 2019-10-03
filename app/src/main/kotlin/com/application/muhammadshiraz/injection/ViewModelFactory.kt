package com.sevenpeakssoftware.muhammadshiraz.injection

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.support.v7.app.AppCompatActivity
import com.sevenpeakssoftware.muhammadshiraz.model.database.AppDatabase
import com.sevenpeakssoftware.muhammadshiraz.ui.article.CarListViewModel

class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CarListViewModel::class.java)) {
            val db = Room.databaseBuilder(activity.applicationContext, AppDatabase::class.java, "Cars").build()
            @Suppress("UNCHECKED_CAST")
            return CarListViewModel(db.CarDao()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}