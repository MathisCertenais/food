package com.example.messycookingapp.data.states.sources

import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.repository.RecipeSource
import com.squareup.moshi.Json
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import retrofit2.http.GET
import javax.inject.Singleton


object RecipeSource: RecipeSource {

    private const val BASE_URL = "https://api.spoonacular.com"



    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .build()

    data class MissedIngredients (

        @Json(name = "aisle")
        val aisle : String,

        @Json(name = "amount")
        val amount : Int,

        @Json(name = "id")
        val id : Int,

        @Json(name = "image")
        val image : String,

        @Json(name = "meta")
        val meta : ArrayList<String>,

        @Json(name = "name")
        val name : String,

        @Json(name = "original")
        val original : String,

        @Json(name = "originalName")
        val originalName : String,

        @Json(name = "unit")
        val unit : String,

        @Json(name = "unitLong")
        val unitLong : String,

        @Json(name = "unitShort")
        val unitShort : String,

        )

    data class UsedIngredients (

        @Json(name = "aisle")
        val aisle : String,

        @Json(name = "amount")
        val amount : Int,

        @Json(name = "id")
        val id : Int,

        @Json(name = "image")
        val image : String,

        @Json(name = "meta")
        val meta : ArrayList<String>,

        @Json(name = "name")
        val name : String,

        @Json(name = "original")
        val original : String,

        @Json(name = "originalName")
        val originalName : String,

        @Json(name = "unit")
        val unit : String,

        @Json(name = "unitLong")
        val unitLong : String,

        @Json(name = "unitShort")
        val unitShort : String,

        )
    data class RecipeAppList (
        @Json(name = "applist")
        val appList: List<RecipeModel>
    )

    data class onlineRecipeModel(

        @Json(name = "id")
        val id : Int,

        @Json(name = "image")
        val image : String,

        @Json(name = "imageType")
        val imageType : String,

        @Json(name = "likes")
        val likes : Int,

        @Json(name = "missedIngredientCount")
        val missedIngredientCount : Int,

        @Json(name = "missedIngredients")
        val missedIngredients : ArrayList<MissedIngredients>,

        @Json(name = "title")
        val title : String,

        @Json(name = "unusedIngredients")
        val unusedIngredients : ArrayList<String>,

        @Json(name = "usedIngredientCount")
        val usedIngredientCount : Int,

        @Json(name = "usedIngredients")
        val usedIngredients : ArrayList<UsedIngredients>,

        )

    interface SteamAppsService{
        @GET("recipes/findByIngredients?apiKey=e89adcbd21a84ae2a6365c6fe739650f&ingredients=apples,+flour,+sugar&number=2?format=json")
        suspend fun findByIngredients() : RecipeAppList
    }

    private val retrofitSteamAppsServiceService: SteamAppsService by lazy {
        retrofit.create(SteamAppsService::class.java)
    }

    override suspend fun getRecipe(): List<RecipeModel> {
        return retrofitSteamAppsServiceService.findByIngredients()
            .appList
            .map {
            RecipeModel(it.id,
            it.image,
            it.imageType,
            it.likes,
            it.missedIngredientCount,
            it.missedIngredients,
            it.title,
            it.unusedIngredients,
            it.usedIngredientCount,
            it.usedIngredients)
        }

    }
}

@InstallIn(SingletonComponent::class)
@Module
object RecipeSourceModule {
    @Provides
    @Singleton
    fun provideGameSource(): RecipeSource {
        return RecipeSource
    }
}