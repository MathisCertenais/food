package com.example.messycookingapp

import android.app.Application
import com.example.messycookingapp.data.states.AppContainer
import com.example.messycookingapp.data.states.DefaultAppContainer
import com.example.messycookingapp.data.states.TestAppContainer

class LanOrganizerApplication : Application() {

    val testing = false
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}