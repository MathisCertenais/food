package com.example.messycookingapp.data.states.repository

import com.example.messycookingapp.data.states.models.RecipeModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


interface RecipeSource  {
    suspend fun getRecipe(): List<RecipeModel>
}

interface RecipeRepository {
    suspend fun getRecipe(): List<RecipeModel>
}


class DefaultRecipeRepository() : RecipeRepository{
    @Inject
    lateinit var RecipeSource: RecipeSource

    override suspend fun getRecipe(): List<RecipeModel>{
        return RecipeSource.getRecipe()
    }




}


@InstallIn(SingletonComponent::class)
@Module
object RecipeRepositoryModule {
    @Provides
    fun provideRecipeRepo(): RecipeRepository{
        return DefaultRecipeRepository()
    }
}

