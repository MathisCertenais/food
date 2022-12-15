package com.example.messycookingapp.data.states


import com.example.messycookingapp.data.states.repository.DefaultRecipeRepository
import com.example.messycookingapp.data.states.repository.RecipeRepository
import com.example.messycookingapp.data.states.sources.RecipeSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

interface AppContainer {
    val recipeSource : RecipeSource
    val recipeRepository : RecipeRepository
}

abstract class DefaultAppContainer : AppContainer {
    private companion object {
        private const val BASE_URL = "https://api.spoonacular.com"

        private val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        private val retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(BASE_URL)
            .build()
    }
    override val recipeSource: RecipeSource
        get() = TODO("Not yet implemented")

/**
    override val recipeRepository: RecipeRepository by lazy {
        DefaultRecipeRepository()
    }
    */

}




