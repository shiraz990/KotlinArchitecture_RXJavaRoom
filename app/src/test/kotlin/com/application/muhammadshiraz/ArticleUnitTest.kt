package com.application.muhammadshiraz

import android.arch.persistence.room.Room
import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.application.muhammadshiraz.model.Article
import com.application.muhammadshiraz.model.ArticleDao
import com.application.muhammadshiraz.model.Car
import com.application.muhammadshiraz.model.CarDao
import com.application.muhammadshiraz.model.database.AppDatabase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class ArticleUnitTest {

    lateinit var instrumentationContext: Context

    private lateinit var todoDao: ArticleDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {

        db = Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getTargetContext(), AppDatabase::class.java).build()
        todoDao = ArticleDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val todo= Article(0, "Test","08.02.2019 03:50","test subject","http://sampleimage.com/img","test description")
        todoDao.insertAll(todo)
        val todoItem = todoDao.all

    }





}