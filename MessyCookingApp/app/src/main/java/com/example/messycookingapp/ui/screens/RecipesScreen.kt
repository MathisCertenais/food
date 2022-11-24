package com.example.messycookingapp.ui.screens

import android.content.Intent
import androidx.annotation.ColorRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.messycookingapp.R
import com.example.messycookingapp.data.states.repository.DefaultRecipeRepository
import com.example.messycookingapp.ui.theme.MessyCookingAppTheme
import com.example.messycookingapp.ui.viewmodels.LanViewModel
import com.example.messycookingapp.ui.viewmodels.RecipeviewModel
import java.time.format.TextStyle

@Composable
fun Recipes(navController: NavController, modifier : Modifier=Modifier){
    //val viewModel : RecipeviewModel = viewModel(factory = RecipeviewModel.Factory)
    //val recipeList = viewModel.uiState.collectAsState()
    val context = LocalContext.current
    Column (modifier = modifier
        .fillMaxWidth()
        .fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.red_logo)),
        onClick = {
            val intent=Intent(Intent.ACTION_SEND).apply {
                type="text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "Look this recipe !")
            }
            context.startActivity(Intent.createChooser(intent, "Share my recipe"))

        }) {
            Text(text = "Share this recipe", color = Color.White, fontFamily = FontFamily(Font(R.font.capriola)), fontSize = 16.sp)


        }

    }
}

