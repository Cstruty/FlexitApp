package com.example.flexit

import android.app.Application
import com.example.flexit.nutrition_repository.NutritionRepositoryLocalDB
import com.example.flexit.nutrition_repository.NutritionRepositoryRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class FlexitApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { NutritionRepositoryLocalDB.getDatabase(this, applicationScope) }
    val repository by lazy { NutritionRepositoryRepository(database.foodDAO()) }
}