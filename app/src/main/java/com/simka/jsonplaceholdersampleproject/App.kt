package com.simka.jsonplaceholdersampleproject

import android.app.Application
import com.simka.jsonplaceholdersampleproject.di.databaseModule
import com.simka.jsonplaceholdersampleproject.di.networkingModule
import com.simka.jsonplaceholdersampleproject.di.repositoryModule
import com.simka.jsonplaceholdersampleproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkingModule, repositoryModule, viewModelModule, databaseModule))
        }
    }
}