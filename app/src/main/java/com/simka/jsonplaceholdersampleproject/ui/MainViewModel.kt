package com.simka.jsonplaceholdersampleproject.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.repository.PhotosRepository
import kotlinx.coroutines.flow.Flow

class MainViewModel(private val libraryRepository: PhotosRepository) : ViewModel() {

    fun fetchPhotos(): Flow<PagingData<Photo>> {
        return libraryRepository.fetchPhotos().cachedIn(viewModelScope)
    }

}