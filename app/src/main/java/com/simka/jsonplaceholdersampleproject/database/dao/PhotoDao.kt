package com.simka.jsonplaceholdersampleproject.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.simka.jsonplaceholdersampleproject.model.Photo

@Dao
interface PhotoDao {

    @Insert(onConflict = REPLACE)
    suspend fun savePhotos(photos: List<Photo>)

    @Query("SELECT * FROM photos")
    fun getPhotos(): LiveData<Photo>
}