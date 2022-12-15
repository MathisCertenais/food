package com.example.messycookingapp

import android.app.Application
import android.content.Context
import com.example.messycookingapp.data.states.AppContainer
import com.example.messycookingapp.data.states.AppDatabase
import com.example.messycookingapp.data.states.DefaultAppContainer
import dagger.hilt.android.HiltAndroidApp

class LanOrganizerApplication : Application() {
    val database : AppDatabase by lazy { AppDatabase.getDatabase(this) }
    companion object {
        private var sApplication: Application? = null

        private fun getApplication(): Application? {
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


