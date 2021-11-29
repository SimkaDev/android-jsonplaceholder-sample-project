package com.simka.jsonplaceholdersampleproject.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.model.Photo
import kotlinx.coroutines.flow.Flow

class PhotosRepositoryImpl(private val photoApiService: ApiService, private val database: JsonPlaceholderDatabase):
    PhotosRepository {

    @ExperimentalPagingApi
    override fun fetchPhotos() : Flow<PagingData<Photo>> {
        return Pager(PagingConfig(pageSize = 30),
            remoteMediator = PhotoRemoteMediator(photoApiService, database),
            pagingSourceFactory = { database.photoDao().getPhotos() }
        ).flow
    }
}