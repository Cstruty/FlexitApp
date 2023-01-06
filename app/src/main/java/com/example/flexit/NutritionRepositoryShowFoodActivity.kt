package com.example.flexit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flexit.nutrition_repository.food.Food
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NutritionRepositoryShowFoodActivity : AppCompatActivity() {

    private val passFoodIntentString = "addFood"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_repository_show_food)
        val foodJson = intent.getStringExtra(passFoodIntentString)
        if (foodJson.isNullOrBlank()) {
            finish()
        } else {
        }
        val food: Food? = foodJson?.let { Json.decodeFromString<Food>(it) }

        if (food != null) {
            setAllFields(food)
        }
    }

    private fun setAllFields(food : Food) {
        return
    }
}