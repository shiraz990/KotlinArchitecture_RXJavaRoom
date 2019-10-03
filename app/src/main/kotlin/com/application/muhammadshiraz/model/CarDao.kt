package com.sevenpeakssoftware.muhammadshiraz.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface CarDao {
    @get:Query("SELECT * FROM Car")
    val all: List<Car>

    @Insert
    fun insertAll(vararg car: Car)
}