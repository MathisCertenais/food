package com.example.messycookingapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.sources.RecipeSource
import com.example.messycookingapp.data.states.states.RecipeUIState
import com.example.messycookingapp.ui.viewmodels.RecipeviewModel
import dagger.hilt.android.lifecycle.HiltViewModel


@Composable
fun ShoppingList(navController: NavController, viewModel: RecipeviewModel = hiltViewModel(), modifier: Modifier = Modifier){

    val recipeList = viewModel.uiState.collectAsState()

    // It works but do not return the value because the recipeList is empty
    Column(
        modifier = modifier.fillMaxWidth().fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Je suis ici "+recipeList.value.size.toString())
        Spacer(modifier = Modifier.height(32.dp))
        Text(text = "oui")
    }


    // Method from internet
    /**
    Scaffold(
    content = {
    RecipeList(recipeList = recipeList.value, modifier.padding(it))
    }
    )
     */
}

@Composable
private fun RecipeList(recipeList: List<RecipeUIState>, modifier: Modifier = Modifier) {

    Text(text = recipeList.size.toString())
    LazyColumn() {
        items(count = recipeList.size,
            contentType = {
                it
                Log.i("ITvalue", it.toString())

            }){
            GameCard(recipe = recipeList[it])

        }
    }
}

@Composable
private fun GameCard(recipe: RecipeUIState, modifier: Modifier = Modifier) {

    Card(modifier = modifier.padding(8.dp)) {
        Row() {
            Text(text = recipe.title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.body1)
        }
    }


}
