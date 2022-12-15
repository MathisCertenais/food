package com.example.messycookingapp.data.states.models

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.messycookingapp.data.states.sources.RecipeSource
import com.squareup.moshi.Json

@Entity(tableName = "recipe")
data class RecipeModel(

    @NonNull @ColumnInfo(name = "name")@Json(name = "name")
    var name: String,

    @NonNull @ColumnInfo(name = "app_id")@Json(name = "appid")
    var appImage: String,

    @PrimaryKey
    @Json(name = "id")
    val id : String,

    @Json(name = "image")
    val image : String,

    @Json(name = "imageType")
    val imageType : String,

    @Json(name = "likes")
    val likes : String,

    @Json(name = "missedIngredientCount")
    val missedIngredientCount : String,

    @Json(name = "missedIngredients")
    val missedIngredients : String,

    @Json(name = "title")
    val title : String,

    @Json(name = "unusedIngredients")
    val unusedIngredients : String,

   @Json(name = "usedIngredientCount")
    val usedIngredientCount : String,

    @Json(name = "usedIngredients")
    val usedIngredients : String,
)
