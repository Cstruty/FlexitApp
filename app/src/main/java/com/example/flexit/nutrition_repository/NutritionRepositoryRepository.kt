package com.example.flexit.nutrition_repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.flexit.nutrition_repository.food.Food


class NutritionRepositoryRepository(private val nutritionDAO: NutritionDAO) {

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertFood(food: Food) {
        nutritionDAO.insertFood(food)
    }

    @WorkerThread
    fun search(foodName: String): LiveData<List<Food>> {
        val foodNameInput = "$foodName%"
        return nutritionDAO.searchFoods(foodNameInput).asLiveData()
    }

    @WorkerThread
    fun getAllFoods(): LiveData<List<Food>> {
        return nutritionDAO.getAllFoods().asLiveData()
    }

    @WorkerThread
    suspend fun resetDatabase() {
        nutritionDAO.deleteAll()
    }
}