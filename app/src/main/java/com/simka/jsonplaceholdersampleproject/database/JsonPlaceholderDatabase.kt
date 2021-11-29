package com.simka.jsonplaceholdersampleproject.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.simka.jsonplaceholdersampleproject.database.dao.PhotoDao
import com.simka.jsonplaceholdersampleproject.model.Photo

@Database(
    entities = [Photo::class],
    version = 1,
    exportSchema = false
)
abstract class JsonPlaceholderDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}