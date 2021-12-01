package com.simka.jsonplaceholdersampleproject.di

import com.simka.jsonplaceholdersampleproject.ui.MainViewModel
import com.simka.jsonplaceholdersampleproject.ui.PhotoDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { PhotoDetailViewModel() }
}