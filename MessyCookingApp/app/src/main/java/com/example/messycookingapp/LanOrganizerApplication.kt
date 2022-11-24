package com.example.messycookingapp

import android.app.Application
import com.example.messycookingapp.data.states.AppContainer
import com.example.messycookingapp.data.states.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LanOrganizerApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}


