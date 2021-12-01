package com.simka.jsonplaceholdersampleproject

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.di.roomTestModule
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
class PhotoKeysDaoTest : KoinTest {

    private val database: JsonPlaceholderDatabase  by inject()

    private val photoKeyFactory = PhotoKeyFactory()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    /**
     * Override default Koin configuration to use Room in-memory database
     */
    @Before
    fun init() {
        loadKoinModules(roomTestModule)
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