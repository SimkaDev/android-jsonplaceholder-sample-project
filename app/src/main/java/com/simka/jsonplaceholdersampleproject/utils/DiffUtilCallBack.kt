package com.simka.jsonplaceholdersampleproject.utils

import androidx.recyclerview.widget.DiffUtil
import com.simka.jsonplaceholdersampleproject.model.Photo

class DiffUtilCallBack : DiffUtil.ItemCallback<Photo>() {
    override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
        return oldItem.id == newItem.id
                && oldItem.albumId == newItem.albumId
                && oldItem.title == newItem.title
                && oldItem.url == newItem.url
                && oldItem.thumbnailUrl == newItem.thumbnailUrl
    }
}