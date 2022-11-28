package com.example.messycookingapp

import android.app.Application
import android.content.Context
import com.example.messycookingapp.data.states.AppContainer
import com.example.messycookingapp.data.states.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class LanOrganizerApplication : Application() {

    companion object {
        private var sApplication: Application? = null

        fun getApplication(): Application? {
            return sApplication
        }

        fun getContext(): Context? {
            return getApplication()!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        sApplication = this
    }
}


