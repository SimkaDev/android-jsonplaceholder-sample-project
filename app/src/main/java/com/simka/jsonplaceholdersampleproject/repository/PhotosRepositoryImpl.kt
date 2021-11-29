package com.simka.jsonplaceholdersampleproject.repository

import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import com.simka.jsonplaceholdersampleproject.model.Photo

class PhotosRepositoryImpl(private val photoApiService: ApiService, private val database: JsonPlaceholderDatabase):
    PhotosRepository {

    override fun fetchPhotos() : List<Photo> {
        // TODO
        return listOf<Photo>()
    }
}