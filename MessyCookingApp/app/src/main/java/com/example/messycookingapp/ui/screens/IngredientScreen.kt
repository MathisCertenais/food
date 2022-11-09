package com.example.messycookingapp.ui.screens

import android.util.Log
import android.widget.EditText
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.messycookingapp.R
import com.example.messycookingapp.ui.theme.MessyCookingAppTheme



@Composable
fun Ingredients(navController: NavController,modifier : Modifier = Modifier)
{

    val listIngredient = remember {
        mutableStateListOf<String>()
    }

    val newIngredient = remember { mutableStateOf(TextFieldValue()) }

    Column {
        Spacer(modifier = Modifier.height((30.dp)))
        Row(
            Modifier
                .width(400.dp)
                .height(60.dp)
        ) {
            Spacer(modifier = Modifier.width((18.dp)))
            TextField(
                value = newIngredient.value,
                onValueChange = {
                    newIngredient.value = it
                },
                modifier = Modifier.weight(0.8f),
                placeholder = { Text(text = "Enter an ingredient",fontFamily = FontFamily(Font(R.font.capriola))) },
                textStyle = TextStyle(
                    color = Color.Black, fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.capriola))
                ),
                colors  = TextFieldDefaults.textFieldColors(
                    backgroundColor = colorResource(id = (R.color.text_field_color)),
                    focusedIndicatorColor = colorResource(id = R.color.red_logo),
                    cursorColor = Color.Black, trailingIconColor = Color.Black),

                maxLines = 1,
                singleLine = true
            )
            Spacer(modifier = Modifier.width((10.dp)))
            Button(colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.red_logo)),
                onClick = {
                    listIngredient.add(newIngredient.value.text)
                    newIngredient.value=TextFieldValue()
                },
                modifier = Modifier
                    .weight(0.2f)
                    .fillMaxHeight()
            ) {
                Text(text = "ADD",
                    color=Color.White,
                    fontFamily = FontFamily(Font(R.font.capriola)))
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Surface(modifier = Modifier.padding(all = Dp(3f))) {
            LazyColumn (Modifier.padding(start = 10.dp )
                .height(340.dp)
                .width(400.dp)){

                itemsIndexed(listIngredient) { index,item ->
                    Row{
                        Icon(painter = painterResource(id =R.drawable.circle),
                            contentDescription = null,
                            Modifier
                                .size(20.dp)
                                .padding(top = 7.dp) )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = item,
                            Modifier
                                .weight(0.80f)
                                .padding(top = 2.dp),
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.capriola))
                        )
                    IconButton(modifier = Modifier
                        .padding(end = 80.dp, bottom=3.dp)
                        .size(width = 50.dp, height = 30.dp),
                        onClick = { listIngredient.removeAt(index) },
                        )
                    {
                        Icon(painter = painterResource(id = R.drawable.trash), tint =Color.Black,
                            contentDescription = null )
                        
                    }
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }

            }
        Button(modifier=Modifier.align(Alignment.CenterHorizontally).padding(top=20.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.red_logo)),

            onClick = {
            },
        )
             {
            Icon(imageVector =Icons.Filled.Search, contentDescription = null,
                modifier = Modifier.padding(start = 2.dp,end=10.dp))
            Text(text = "SEARCH",
                color=Color.White,
                fontFamily = FontFamily(Font(R.font.capriola)))
        }
    }

}


