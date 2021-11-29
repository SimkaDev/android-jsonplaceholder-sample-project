package com.simka.jsonplaceholdersampleproject.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.simka.jsonplaceholdersampleproject.model.Photo

class PhotoDetailViewModel() : ViewModel() {

    private val _photoDetails = MutableLiveData<Photo>()
    val photoDetailsLiveData: LiveData<Photo> = _photoDetails

    fun setPhoto(photo: Photo) {
        _photoDetails.postValue(photo)
    }

}