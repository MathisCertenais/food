package com.example.messycookingapp.data.states

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.messycookingapp.LanOrganizerApplication
import com.example.messycookingapp.data.states.models.RecipeModel
import com.example.messycookingapp.data.states.sources.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [RecipeModel::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "lan_organizer_database").build()
                INSTANCE = instance

                instance
            }
        }
    }
}

@InstallIn(SingletonComponent::class)
@Module
object AppDatabaseModule {
    @Provides
    @Singleton
    fun provideAppDatabase(): AppDatabase {
        return AppDatabase.getDatabase(LanOrganizerApplication.getContext()!!)
    }
}