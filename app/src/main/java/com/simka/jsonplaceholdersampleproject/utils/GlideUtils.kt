package com.simka.jsonplaceholdersampleproject.utils

import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders

object GlideUtils {

    private const val key = "User-Agent"
    private const val value = "my-user-agent"

    fun getGlideUrl(url: String) = GlideUrl(
        url, LazyHeaders.Builder()
            .addHeader(key, value)
            .build()
    )
}