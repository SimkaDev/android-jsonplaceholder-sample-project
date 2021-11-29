package com.simka.jsonplaceholdersampleproject.di

import com.simka.jsonplaceholdersampleproject.repository.PhotosRepository
import com.simka.jsonplaceholdersampleproject.repository.PhotosRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single { PhotosRepositoryImpl(get(), get()) as PhotosRepository }
}