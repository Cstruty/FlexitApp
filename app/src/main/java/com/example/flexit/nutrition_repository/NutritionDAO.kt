package com.example.flexit.nutrition_repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.flexit.nutrition_repository.food.Food
import kotlinx.coroutines.flow.Flow

@Dao
interface NutritionDAO {

    @Query("SELECT * FROM foods ORDER BY food_name ASC")
    fun getAllFoods(): Flow<List<Food>>

//    @Query("SELECT :food_name FROM foods")
//    suspend fun getFood(food_name: String): Food

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFood(food: Food?)

    @Query("SELECT * FROM foods where food_name LIKE :foodName ORDER BY food_name ASC")
    fun searchFoods(foodName: String): Flow<List<Food>>

    @Query("DELETE FROM foods")
    suspend fun deleteAll()
}