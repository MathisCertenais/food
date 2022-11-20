package com.example.messycookingapp.data.states.models

import com.example.messycookingapp.data.states.sources.RecipeSource
import com.squareup.moshi.Json

data class RecipeModel(
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
    val missedIngredients : ArrayList<RecipeSource.MissedIngredients>,

    @Json(name = "title")
    val title : String,

    @Json(name = "unusedIngredients")
    val unusedIngredients : ArrayList<String>,

    @Json(name = "usedIngredientCount")
    val usedIngredientCount : Int,

    @Json(name = "usedIngredients")
    val usedIngredients : ArrayList<RecipeSource.UsedIngredients>,
)
