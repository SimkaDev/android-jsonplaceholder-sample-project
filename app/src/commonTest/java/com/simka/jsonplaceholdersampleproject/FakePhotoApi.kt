package com.simka.jsonplaceholdersampleproject

import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.model.Photo
import java.lang.Integer.min

class FakePhotoApi : ApiService {

    private val model = mutableListOf<Photo>()

    fun addPhoto(photo: Photo) {
        model.add(photo)
    }

    override suspend fun fetchPhotos(limit: Int, start: Int): List<Photo> {
        return findPhotos(limit, start)
    }

    private fun findPhotos(limit: Int, start: Int): List<Photo> {
        val index = model.indexOfFirst { it.id == start.toLong() }
        if (index == -1) {
            return emptyList()
        }

        return model.subList(start, min(model.size, start + limit))
    }

    fun clearPhotos() {
        model.clear()
    }
}