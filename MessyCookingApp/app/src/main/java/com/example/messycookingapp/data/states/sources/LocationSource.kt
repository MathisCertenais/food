package com.example.messycookingapp.data.states.sources

import com.example.messycookingapp.data.states.models.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

object LocationSource {
    suspend fun getLocation(): Location {
        TODO()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object LocationSourceModule {
    @Provides
    @Singleton
    fun provideLocationSource(): LocationSource {
        return LocationSource
    }
}