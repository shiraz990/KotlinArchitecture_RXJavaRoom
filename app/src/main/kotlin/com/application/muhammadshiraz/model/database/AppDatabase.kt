package com.sevenpeakssoftware.muhammadshiraz.model.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sevenpeakssoftware.muhammadshiraz.model.Car
import com.sevenpeakssoftware.muhammadshiraz.model.CarDao

@Database(entities = [Car::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun CarDao(): CarDao
}