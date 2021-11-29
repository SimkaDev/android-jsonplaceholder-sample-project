package com.simka.jsonplaceholdersampleproject.ui

import androidx.lifecycle.ViewModel
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.repository.PhotosRepository

class MainViewModel(private val libraryRepository: PhotosRepository) : ViewModel() {

    fun fetchPhotos(): List<Photo> {
        return libraryRepository.fetchPhotos()
    }

}