package com.simka.jsonplaceholdersampleproject.api

import com.simka.jsonplaceholdersampleproject.model.Photo
import retrofit2.http.*

interface ApiService {

    @GET("photos")
    suspend fun fetchPhotos(
        @Query("_limit") limit: Int = 0,
        @Query("_start") start: Int = 0
    ): List<Photo>

}