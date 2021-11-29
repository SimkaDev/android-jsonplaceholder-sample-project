package com.simka.jsonplaceholdersampleproject.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.simka.jsonplaceholdersampleproject.R
import com.simka.jsonplaceholdersampleproject.databinding.PhotoDetailFragmentBinding
import com.simka.jsonplaceholdersampleproject.model.Photo
import com.simka.jsonplaceholdersampleproject.utils.GlideUtils
import org.koin.android.viewmodel.ext.android.viewModel

class PhotoDetailFragment: Fragment() {

    private var _binding: PhotoDetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: PhotoDetailViewModel by viewModel<PhotoDetailViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = PhotoDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            val safeArgs = PhotoDetailFragmentArgs.fromBundle(it)
            val photo : Photo = safeArgs.selectedPhoto
            viewModel.setPhoto(photo)
        }

        initObservers()
    }

    private fun initObservers() {
        viewModel.photoDetailsLiveData.observe(viewLifecycleOwner, Observer { photoDetail ->
            binding.titleTextView.text = photoDetail.title
            binding.albumTextView.text = getString(R.string.album_number, photoDetail.albumId)

            Glide.with(this)
                .load(GlideUtils.getGlideUrl(photoDetail.url))
                .placeholder(R.color.white)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        binding.progressBar.visibility = View.GONE;
                        return false
                    }
                })
                .into(binding.photoImageView)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}