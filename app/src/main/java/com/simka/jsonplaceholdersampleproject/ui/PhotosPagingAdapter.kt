package com.simka.jsonplaceholdersampleproject.ui

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
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
                .placeholder(R.color.white)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        itemView.findViewById<ProgressBar>(R.id.item_progress_bar).visibility = View.GONE;
                        return false
                    }
                })
                .into(itemView.findViewById(R.id.photoImage))
        }
    }
}