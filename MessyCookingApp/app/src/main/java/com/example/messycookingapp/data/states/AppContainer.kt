package com.example.messycookingapp.data.states

import com.example.messycookingapp.data.states.repository.CachedDefaultRecipeRepository
import com.example.messycookingapp.data.states.repository.DefaultRecipeRepository
import com.example.messycookingapp.data.states.repository.RecipeRepository
import com.example.messycookingapp.data.states.sources.RecipeSource

interface AppContainer {
    val recipeSource : RecipeSource
    val recipeRepository : RecipeRepository
}

class DefaultAppContainer : AppContainer {
    override val recipeSource: RecipeSource by lazy {
        RecipeSource
    }

    override val recipeRepository: RecipeRepository by lazy {
        DefaultRecipeRepository(recipeSource)
    }

}

class TestAppContainer : AppContainer {
    override val recipeSource: RecipeSource by lazy {
        RecipeSource
    }
    override val recipeRepository: RecipeRepository by lazy {
        CachedDefaultRecipeRepository(recipeSource, RecipeSource)
    }

}