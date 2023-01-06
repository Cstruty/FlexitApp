package com.example.flexit.nutrition_repository

import androidx.lifecycle.*
import com.example.flexit.nutrition_repository.food.Food
import kotlinx.coroutines.launch

class FoodViewModel(private val repository: NutritionRepositoryRepository) : ViewModel() {

    fun search(foodName: String): LiveData<List<Food>> {
        return repository.search(foodName)
    }

    fun getAllFoods(): LiveData<List<Food>> {
        return repository.getAllFoods()
    }

    fun insert(food: Food) = viewModelScope.launch {
        repository.insertFood(food)
    }

    fun clearAllFoods() = viewModelScope.launch {
        repository.resetDatabase()
    }
}

class FoodViewModelFactory(private val repository: NutritionRepositoryRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FoodViewModel::class.java)) {
            @Suppress("Unchecked Cast") return FoodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewClass Model")
    }
}