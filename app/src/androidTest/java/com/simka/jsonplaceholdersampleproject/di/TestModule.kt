package com.simka.jsonplaceholdersampleproject.di

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.simka.jsonplaceholdersampleproject.FakePhotoApi
import com.simka.jsonplaceholdersampleproject.PhotoFactory
import com.simka.jsonplaceholdersampleproject.api.ApiService
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import org.koin.dsl.module

private val postFactory = PhotoFactory()

val apiTestModule = module(override = true) {
    single {
        FakePhotoApi().apply {
            addPhoto(postFactory.createPhoto())
            addPhoto(postFactory.createPhoto())
            addPhoto(postFactory.createPhoto())
        } as ApiService
    }
}

val apiEmptyTestModule = module(override = true) {
    single {
        FakePhotoApi() as ApiService
    }
}

val roomTestModule = module(override = true) {
    single {
        // In-Memory database config
        Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            JsonPlaceholderDatabase::class.java
        )
            .allowMainThreadQueries()
            .build()
    }
}