package com.sevenpeakssoftware.muhammadshiraz

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.sevenpeakssoftware.muhammadshiraz.model.Car
import com.sevenpeakssoftware.muhammadshiraz.model.CarDao
import com.sevenpeakssoftware.muhammadshiraz.model.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException
import org.junit.runners.JUnit4



@RunWith(AndroidJUnit4::class)
class CarUnitTest {

    lateinit var instrumentationContext: Context

    private lateinit var todoDao: CarDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {

        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getTargetContext(), AppDatabase::class.java).build()
        todoDao = db.CarDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val todo= Car(0, "Test","08.02.2019 03:50","test subject","http://sampleimage.com/img","test description")
        todoDao.insertAll(todo)
        val todoItem = todoDao.all

    }





}