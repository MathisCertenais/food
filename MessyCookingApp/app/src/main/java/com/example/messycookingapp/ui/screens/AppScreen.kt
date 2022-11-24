package com.example.messycookingapp.ui.screens
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.messycookingapp.R
import com.example.messycookingapp.ui.viewmodels.RecipeviewModel


sealed class MainScreens(val route: String, @StringRes val title: Int, val icon : ImageVector){

    //object Ingredient : MainScreens(route = "Ingredient", R.string.ingredient, R.drawable.ic_baseline_storefront_24)
    //object Recipes : MainScreens(route = "Recipes", R.string.recipes, R.drawable.ic_baseline_menu_book_24)
    //object ShoppingList : MainScreens(route = "ShoppingList", R.string.shopping_list, R.drawable.ic_baseline_menu_book_24)
    //object Profile : MainScreens(route = "Profile", R.string.profile, R.drawable.ic_baseline_menu_book_24)

    object Ingredient : MainScreens(route = "Ingredient", R.string.ingredient, Icons.Filled.Search)
    object Recipes : MainScreens(route = "Recipes", R.string.recipes, Icons.Filled.Menu)
    object ShoppingList : MainScreens(route = "ShoppingList", R.string.shopping_list, Icons.Filled.ShoppingCart)
    object Profile : MainScreens(route = "Profile", R.string.profile, Icons.Filled.Face)
}

@Composable
fun TopBar(@StringRes title: Int,
           canNavigateBack: Boolean,
           modifier: Modifier=Modifier,

           navigateBack: ()->Unit){
    TopAppBar(
        backgroundColor = colorResource(R.color.red_logo ),
        title = {
            Text(stringResource(id = title), fontSize = 32.sp,
            color = Color.White, fontFamily = FontFamily(Font((R.font.damion)))
            ) }, modifier=modifier, navigationIcon = {
            if(canNavigateBack){
                IconButton(onClick = navigateBack){
                    Icon(imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back_button)
                                        )
                }
            }
        }
    )
}

@Composable
fun BottomBar(modifier: Modifier=Modifier, navController: NavController,
                OnMainScreenChanged: (MainScreens)-> Unit){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val items = listOf(MainScreens.Ingredient,
        MainScreens.Recipes,
        MainScreens.ShoppingList,
        MainScreens.Profile)
    
    BottomAppBar((modifier),backgroundColor = colorResource(R.color.red_logo )){
        items.forEach { screen ->
            BottomNavigationItem(
                selectedContentColor = Color.Black,
                icon = { androidx.compose.material.Icon(screen.icon, contentDescription = null) },
                //label = { androidx.compose.material.Text(stringResource(id = screen.title))},
                //icon = {Icon(painter = painterResource(screen.icon), contentDescription = null )},
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                    OnMainScreenChanged(screen)
                }

            )
        }
    }
}


@Composable
fun AppScreen(modifier: Modifier=Modifier){
    val navController= rememberNavController()
    val startDestination = MainScreens.Ingredient
    var currentTitle by remember {
        mutableStateOf(startDestination.title)
    }
    Scaffold(
        topBar = {
            TopBar(title = currentTitle, canNavigateBack = false) {

            }
        },
        bottomBar = {
            BottomBar(navController = navController){
                currentTitle = it.title
            }
        }) {innerPadding ->

        NavHost(navController = navController,
            startDestination = MainScreens.Ingredient.route,
            modifier.padding((innerPadding)))
        {
            composable(MainScreens.Ingredient.route){Ingredients(navController,Modifier)}
            composable(MainScreens.Recipes.route){Recipes(navController,Modifier)}
            composable(MainScreens.ShoppingList.route){ShoppingList(navController)}
            composable(MainScreens.Profile.route){Profile(navController,Modifier)}
        }

    }
}

