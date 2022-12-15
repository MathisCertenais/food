package com.example.messycookingapp.data.states.repository

import com.example.messycookingapp.LanOrganizerApplication
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.sources.RecipeCachedSource
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import java.security.AccessController.getContext
import javax.inject.Inject
import javax.inject.Singleton
import com.example.messycookingapp.data.states.repository.RecipeSource as RecipeSource


interface RecipeSource  {
    suspend fun getRecipe(): List<RecipeModel>
}

interface RecipeRepository {
    suspend fun getRecipe(): List<RecipeModel>
}

/**class DefaultRecipeRepository() : RecipeRepository{
    @Inject
    lateinit var RecipeSource: RecipeSource

    override suspend fun getRecipe(): List<RecipeModel>{
        return RecipeSource.getRecipe()
    }*/

class DefaultRecipeRepository {

    private var recipeSource: RecipeSource

    init {
        val appContext = LanOrganizerApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultGameRepoEntryPoint::class.java)
            }
        recipeSource = utilitiesEntryPoint?.recipeSource!!
    }


    suspend fun getRecipe(): List<RecipeModel> {
        return recipeSource.getRecipe()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultGameRepoEntryPoint {
    var recipeSource: RecipeSource
}

/**
@InstallIn(SingletonComponent::class)
@Module
object GameSourceModule {
    @Provides
    @Singleton
    fun provideGameSource(): RecipeSource {
        return RecipeCachedSource
    }
}*/