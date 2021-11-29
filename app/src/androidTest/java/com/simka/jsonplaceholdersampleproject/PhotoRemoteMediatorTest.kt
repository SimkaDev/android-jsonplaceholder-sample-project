package com.simka.jsonplaceholdersampleproject

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.repository.PhotoRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertTrue
import kotlin.test.assertFalse

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PhotoRemoteMediatorTest {

    private val photoFactory = PhotoFactory()
    private val mockPhotos = listOf(
        photoFactory.createPhoto(),
        photoFactory.createPhoto(),
        photoFactory.createPhoto()
    )
    private val mockApi = FakePhotoApi()

    private val mockDb = Room.inMemoryDatabaseBuilder(
        ApplicationProvider.getApplicationContext(),
        JsonPlaceholderDatabase::class.java).build()

    @After
    fun tearDown() {
        mockDb.clearAllTables()
        mockApi.clearPhotos()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        // Add mock results for the API to return.
        mockPhotos.forEach { photo -> mockApi.addPhoto(photo) }
        val remoteMediator = PhotoRemoteMediator(
            mockApi,
            mockDb
        )
        val pagingState = PagingState<Int, Photo>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue { result is RemoteMediator.MediatorResult.Success }
        assertFalse { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

    @Test
    fun refreshLoadSuccessAndEndOfPaginationWhenNoMoreData() = runBlocking {
        // To test endOfPaginationReached, don't set up the mockApi to return post
        // data here.
        val remoteMediator = PhotoRemoteMediator(
            mockApi,
            mockDb
        )
        val pagingState = PagingState<Int, Photo>(
            listOf(),
            null,
            PagingConfig(10),
            10
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)
        assertTrue { result is RemoteMediator.MediatorResult.Success }
        assertTrue { (result as RemoteMediator.MediatorResult.Success).endOfPaginationReached }
    }

}