package com.example.messycookingapp.data.states.sources

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.example.messycookingapp.LanOrganizerApplication
import com.example.messycookingapp.data.states.AppDatabase
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.work.RefreshDataWorker
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.time.Instant
import java.util.concurrent.TimeUnit


@Dao
interface GameDao{
    @Query("SELECT * FROM recipe")
    fun getAll(): List<RecipeModel>

    @Query("SELECT * FROM recipe WHERE name = :recipeName")
    fun getByName(recipeName: String): List<RecipeModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: List<RecipeModel>)
}

object RecipeCachedSource {

    private lateinit var appDatabase : AppDatabase

    @RequiresApi(Build.VERSION_CODES.O)
    private var lastUpdateTime: Instant = Instant.now()

    init {
        val appContext = LanOrganizerApplication.getContext()
        val utilitiesEntryPoint =
            appContext?.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultCacheGameSourceEntryPoint::class.java)
            }
        appDatabase = utilitiesEntryPoint?.appDatabase!!

        //Launch a periodic work to update the cache
        val repeatingRequest = OneTimeWorkRequestBuilder<RefreshDataWorker>().build()
        WorkManager.getInstance(LanOrganizerApplication.getContext()!!).enqueue(repeatingRequest);
    }

    suspend fun refreshRecipe(){
        withContext(Dispatchers.IO){
            val recipes = RecipeSource.getRecipe()
            appDatabase.gameDao().insert(recipes)
        }
    }

    suspend fun getRecipe(): List<RecipeModel> {
        //refreshGames()
        return appDatabase.gameDao().getAll()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultCacheGameSourceEntryPoint {
    var appDatabase: AppDatabase
}