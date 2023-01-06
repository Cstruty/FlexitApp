package com.example.flexit.nutrition_repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.flexit.nutrition_repository.food.Food
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Food::class], version = 3)
abstract class NutritionRepositoryLocalDB : RoomDatabase() {

    abstract fun foodDAO(): NutritionDAO

    companion object {
        @Volatile
        private var INSTANCE : NutritionRepositoryLocalDB? = null

        fun getDatabase(context : Context, scope: CoroutineScope) : NutritionRepositoryLocalDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        NutritionRepositoryLocalDB::class.java,
                        "NutritionRepoDatabase"
                ).fallbackToDestructiveMigration()
                    .addCallback(FoodDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }

        private class FoodDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            override fun onCreate( db : SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.foodDAO())
                    }
                }
            }
            suspend fun populateDatabase(foodDao: NutritionDAO) {
//                foodDao.deleteAll()
            }
        }

    }

}