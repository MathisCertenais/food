package com.example.messycookingapp.data.states.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.messycookingapp.data.states.repository.RecipeRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.components.SingletonComponent

class RefreshDataWorker(appContext: Context, params: WorkerParameters) :
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "io.tech4fun.lanorganizer.work.RefreshDataWorker"
    }

    private var gameRepo : RecipeRepository

    init {
        val utilitiesEntryPoint =
            appContext.let {
                EntryPointAccessors.fromApplication(
                    it, DefaultRefreshWorkerEntryPoint::class.java)
            }
        gameRepo = utilitiesEntryPoint.gameRepo

    }

    override suspend fun doWork(): Result {
        try{
            gameRepo.getRecipe();
        }catch (e: Exception){
            return Result.retry()
        }
        return Result.success()
    }
}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DefaultRefreshWorkerEntryPoint {
    var gameRepo : RecipeRepository
}