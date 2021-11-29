package com.simka.jsonplaceholdersampleproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
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
                .error(R.drawable.ic_baseline_error_outline_24)
                .into(binding.photoImageView)
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}