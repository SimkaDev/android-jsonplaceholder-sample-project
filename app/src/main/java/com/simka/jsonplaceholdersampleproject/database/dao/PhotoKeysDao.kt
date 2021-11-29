package com.simka.jsonplaceholdersampleproject.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.simka.jsonplaceholdersampleproject.model.PhotoKey

@Dao
interface PhotoKeysDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePhotoKeys(photoKeys: PhotoKey)

    @Query("SELECT * FROM photoKeys ORDER BY id DESC")
    suspend fun getPhotoKeys(): List<PhotoKey>

}