package com.example.messycookingapp.data.states.repository

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.messycookingapp.data.states.models.RecipeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


interface RecipeSource  {
    suspend fun getRecipe(): List<RecipeModel>
}

interface RecipeRepository {
    suspend fun getRecipe(): List<RecipeModel>
    suspend fun getRecipeFromCache(): List<RecipeModel>
}


class DefaultRecipeRepository(private val RecipeSource: RecipeSource) : RecipeRepository{
    override suspend fun getRecipe(): List<RecipeModel>{
        print("recipe"+RecipeSource.getRecipe())
        return RecipeSource.getRecipe()
    }
    override suspend fun getRecipeFromCache(): List<RecipeModel>{
        return RecipeSource.getRecipe()
    }
}

// coroutine
class Activity : AppCompatActivity(), CoroutineScope {
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launch {
            val result =  callGetApi()
            //onResult(result) // onResult is called on the main thread
        }
    }

    private suspend fun callGetApi(): List<RecipeModel> {  return DefaultRecipeRepository(com.example.messycookingapp.data.states.sources.RecipeSource).getRecipe()
    }

    //private fun onResult(result: List<RecipeModel>): {return result}
}


class CachedDefaultRecipeRepository(val RecipeSource: RecipeSource, val RecipeCachingSource: RecipeSource) : RecipeRepository{
    override suspend fun getRecipe(): List<RecipeModel>{
        TODO( "NOT yet implemented")
    }
    override suspend fun getRecipeFromCache(): List<RecipeModel>{
        TODO( "NOT yet implemented")
    }
}