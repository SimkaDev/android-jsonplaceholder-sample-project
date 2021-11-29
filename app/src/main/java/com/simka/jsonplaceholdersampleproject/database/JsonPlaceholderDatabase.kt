package com.simka.jsonplaceholdersampleproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simka.jsonplaceholdersampleproject.database.dao.PhotoDao
import com.simka.jsonplaceholdersampleproject.database.dao.PhotoKeysDao
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.model.PhotoKey

@Database(
    entities = [Photo::class, PhotoKey::class],
    version = 1,
    exportSchema = false
)
abstract class JsonPlaceholderDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
    abstract fun photoKeysDao(): PhotoKeysDao
}