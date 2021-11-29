package com.simka.jsonplaceholdersampleproject.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "photos")
data class Photo (
    @PrimaryKey
    val id: Long,
    val albumId : Long,
    val title: String,
    val url: String,
    val thumbnailUrl: String
    ) : Parcelable

