package com.simka.jsonplaceholdersampleproject.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.simka.jsonplaceholdersampleproject.databinding.MainFragmentBinding
import com.simka.jsonplaceholdersampleproject.model.Photo
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainFragment: Fragment(), PhotosPagingAdapter.ClickPhotoItemListener {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModel<MainViewModel>()
    private val photosPagingAdapter by lazy { PhotosPagingAdapter(this) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        fetchPhotos()
    }

    private fun fetchPhotos() {
        lifecycleScope.launch {
            viewModel.fetchPhotos().collectLatest { pagingData ->
                photosPagingAdapter.submitData(pagingData)
            }
        }
    }

    private fun setupViews() {
        binding.photosRecyclerView.adapter = photosPagingAdapter

        binding.photosRecyclerView.adapter = photosPagingAdapter.withLoadStateFooter(
            PhotosLoadingAdapter { photosPagingAdapter.retry() }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun selectPhoto(photo: Photo) {
        val action = MainFragmentDirections.actionMainToPhotoDetail(photo, photo.id)
        findNavController().navigate(action)
    }

}