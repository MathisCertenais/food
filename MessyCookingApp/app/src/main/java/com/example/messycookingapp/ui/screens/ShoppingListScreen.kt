package com.example.messycookingapp.ui.screens

import androidx.compose.animation.expandHorizontally
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavController
import com.example.messycookingapp.LogoImage
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.sources.RecipeSource
import com.example.messycookingapp.ui.theme.MessyCookingAppTheme

suspend fun recipe(): List<RecipeModel>{
    return RecipeSource.getRecipe()
}

@Composable
fun ShoppingList(navController: NavController, modifier : Modifier = Modifier){

    // Insert recipe title's
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier) {
        Text(text = "oui")
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "oui")
    }
}


