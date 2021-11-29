package com.simka.jsonplaceholdersampleproject.repository

import com.simka.jsonplaceholdersampleproject.model.Photo

interface PhotosRepository {
    fun fetchPhotos(): List<Photo>
}