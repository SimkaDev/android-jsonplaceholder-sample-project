package com.simka.jsonplaceholdersampleproject.di

import androidx.room.Room
import com.simka.jsonplaceholdersampleproject.database.JsonPlaceholderDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            androidApplication(),
            JsonPlaceholderDatabase::class.java,
            "jsonplaceholder.db").build()
    }
}