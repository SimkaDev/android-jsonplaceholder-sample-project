package com.simka.jsonplaceholdersampleproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PhotoKeysDaoTest {

    private lateinit var database : JsonPlaceholderDatabase

    private val photoKeyFactory = PhotoKeyFactory()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JsonPlaceholderDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @After
    fun closeDb() {
        database.close()
    }

    @InternalCoroutinesApi
    @Test
    fun insertAndGetPhotoKeys() = runBlockingTest {
        val photoToInsert = photoKeyFactory.createPhotoKey()
        val photoToInsert2 = photoKeyFactory.createPhotoKey()
        database.photoKeysDao().savePhotoKeys(photoToInsert)
        database.photoKeysDao().savePhotoKeys(photoToInsert2)

        val photoKeysFromDatabase = database.photoKeysDao().getPhotoKeys()

        assertEquals(photoKeysFromDatabase.size, 2)
        assertEquals(photoKeysFromDatabase[0], photoToInsert2)
        assertEquals(photoKeysFromDatabase[1], photoToInsert)
    }

    @Test
    fun getPhotosWhenNoInserted() = runBlockingTest {
        val photoKeysFromDatabase = database.photoKeysDao().getPhotoKeys()
        assertEquals(photoKeysFromDatabase.size, 0)
    }
}