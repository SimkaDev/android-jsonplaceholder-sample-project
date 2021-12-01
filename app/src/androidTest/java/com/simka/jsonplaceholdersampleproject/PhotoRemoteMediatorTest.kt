package com.simka.jsonplaceholdersampleproject

import androidx.paging.*
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.di.apiEmptyTestModule
import com.simka.jsonplaceholdersampleproject.di.apiTestModule
import com.simka.jsonplaceholdersampleproject.di.roomTestModule
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.repository.PhotoRemoteMediator
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertTrue
import kotlin.test.assertFalse

@ExperimentalPagingApi
@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class PhotoRemoteMediatorTest : KoinTest{

    private val apiService : ApiService by inject()
    private val database: JsonPlaceholderDatabase  by inject()

    @Before
    fun init() {
        loadKoinModules(listOf( roomTestModule))
    }

    @After
    fun tearDown() {
        database.clearAllTables()
    }

    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() = runBlocking {
        loadKoinModules(apiTestModule)

        val remoteMediator = PhotoRemoteMediator(
            apiService,
            database
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
        loadKoinModules(apiEmptyTestModule)

        val remoteMediator = PhotoRemoteMediator(
            apiService,
            database
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