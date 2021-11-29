package com.simka.jsonplaceholdersampleproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photoKeys")
data class PhotoKey (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: Int?
)