package com.simka.jsonplaceholdersampleproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.simka.jsonplaceholdersampleproject.R
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.utils.DiffUtilCallBack
import com.simka.jsonplaceholdersampleproject.utils.GlideUtils

class PhotosPagingAdapter(private val listener: ClickPhotoItemListener) :
    PagingDataAdapter<Photo, PhotosPagingAdapter.PhotoViewHolder>(DiffUtilCallBack()) {

    interface ClickPhotoItemListener {
        fun selectPhoto(photo: Photo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { photo ->
            holder.showData(photo)
            holder.itemView.setOnClickListener {
                listener.selectPhoto(photo)
            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun showData(photo: Photo) = with(itemView) {
            Glide.with(this)
                .load(GlideUtils.getGlideUrl(photo.thumbnailUrl))
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(itemView.findViewById(R.id.photoImage))
        }
    }
}