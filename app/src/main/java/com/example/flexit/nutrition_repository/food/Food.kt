package com.example.flexit.nutrition_repository.food

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "foods")
@Serializable
data class Food(
    @PrimaryKey
    @ColumnInfo(name = "food_name")
    val foodName: String,

    @ColumnInfo(name = "calorie_count")
    val calorieCount: Int,

    @ColumnInfo(name = "protein_quantity")
    val proteinCount: Int,

    @ColumnInfo(name = "carb_quantity")
    val carbohydrateQuantity: Int,

    @ColumnInfo(name = "fat_quantity")
    val fatQuantity: Double
)
