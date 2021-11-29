package com.simka.jsonplaceholdersampleproject.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class Photo (
    @PrimaryKey
    val id: Long,
    val albumId : Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
    )

