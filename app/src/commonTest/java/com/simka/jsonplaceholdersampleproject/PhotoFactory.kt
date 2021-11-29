package com.simka.jsonplaceholdersampleproject

import com.simka.jsonplaceholdersampleproject.model.Photo
import java.util.concurrent.atomic.AtomicInteger

class PhotoFactory {
    private val counter = AtomicInteger(-1)
    fun createPhoto() : Photo {
        val id = counter.incrementAndGet()
        return Photo(
            id = id.toLong(),
            albumId = 1,
            title = "title $id",
            url = "",
            thumbnailUrl = ""
        )
    }
}