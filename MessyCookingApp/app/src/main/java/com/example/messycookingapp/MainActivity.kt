package com.example.messycookingapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.Modifier
import com.example.messycookingapp.data.states.sources.RecipeSource.getRecipe
import com.example.messycookingapp.ui.screens.AppScreen
import com.example.messycookingapp.ui.themes.MessyCookingAppTheme
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MessyCookingAppTheme {
                AppScreen()
            }
        }
    }
}

