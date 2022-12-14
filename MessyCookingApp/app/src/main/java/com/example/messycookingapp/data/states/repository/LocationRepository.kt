package com.example.messycookingapp.data.states.repository

import com.example.messycookingapp.data.states.models.Location
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

interface LocationSource {
    suspend fun getLocation(): Location
}

interface LocationRepository{
    suspend fun getLocation(): Location
}

class DefaultLocationRepository() : LocationRepository {
    @Inject
    lateinit var locationSource: LocationSource

    override suspend fun getLocation(): Location {
        return locationSource.getLocation()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object LocationRepositoryModule {
    @Provides
    fun provideLocationRpo(): LocationRepository {
        return DefaultLocationRepository()
    }
}