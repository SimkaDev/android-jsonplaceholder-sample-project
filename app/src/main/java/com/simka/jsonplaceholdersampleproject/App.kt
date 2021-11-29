package com.simka.jsonplaceholdersampleproject

import android.app.Application
import com.simka.jsonplaceholdersampleproject.di.networkingModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(listOf(networkingModule))
        }
    }
}