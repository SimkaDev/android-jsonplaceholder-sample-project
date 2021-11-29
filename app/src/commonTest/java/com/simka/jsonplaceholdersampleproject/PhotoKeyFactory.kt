package com.simka.jsonplaceholdersampleproject

import com.simka.jsonplaceholdersampleproject.model.PhotoKey
import java.util.concurrent.atomic.AtomicInteger

class PhotoKeyFactory {
    private val counter = AtomicInteger(0)
    fun createPhotoKey() : PhotoKey {
        val id = counter.incrementAndGet()
        return PhotoKey(id, id.plus(1))
    }
}