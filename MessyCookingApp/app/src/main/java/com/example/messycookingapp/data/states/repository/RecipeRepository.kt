package com.example.messycookingapp.data.states.repository

import com.example.messycookingapp.data.states.models.RecipeModel


interface RecipeSource  {
    suspend fun getRecipe(): List<RecipeModel>
}

interface RecipeRepository {
    suspend fun getRecipe(): List<RecipeModel>
    suspend fun getRecipeFromCache(): List<RecipeModel>
}


class DefaultRecipeRepository(val RecipeSource: RecipeSource) : RecipeRepository{
    override suspend fun getRecipe(): List<RecipeModel>{
        return RecipeSource.getRecipe()
    }
    override suspend fun getRecipeFromCache(): List<RecipeModel>{
        return RecipeSource.getRecipe()
    }
}

class CachedDefaultRecipeRepository(val RecipeSource: RecipeSource, val RecipeCachingSource: RecipeSource) : RecipeRepository{
    override suspend fun getRecipe(): List<RecipeModel>{
        TODO( "NOT yet implemented")
    }
    override suspend fun getRecipeFromCache(): List<RecipeModel>{
        TODO( "NOT yet implemented")
    }
}