package com.simka.jsonplaceholdersampleproject.api

import com.simka.jsonplaceholdersampleproject.model.Photo
import retrofit2.http.*

interface ApiService {

    @GET("photos")
    suspend fun fetchPhotos(): List<Photo>
}