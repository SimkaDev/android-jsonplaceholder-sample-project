package com.simka.jsonplaceholdersampleproject.repository

import androidx.paging.PagingData
import com.simka.jsonplaceholdersampleproject.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotosRepository {
    fun fetchPhotos(): Flow<PagingData<Photo>>
}