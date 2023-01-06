package com.example.flexit

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.flexit.FoodListAdapter.FoodViewHolder
import com.example.flexit.nutrition_repository.food.Food
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class FoodListAdapter : ListAdapter<Food, FoodViewHolder>(FOOD_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return FoodViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val current = getItem(position)
        holder.foodNameBind(current.foodName)
        holder.foodCaloriesBind(current.calorieCount)
        holder.foodProteinBind(current.proteinCount)
        holder.foodCarbsBind(current.carbohydrateQuantity)
        holder.foodFatBind(current.fatQuantity)
        holder.detailFoodBind(current)
    }

    class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val foodItemView: TextView = itemView.findViewById<TextView>(R.id.food_name_textbox)
        private val caloriesItemView: TextView =
            itemView.findViewById<TextView>(R.id.calories_count_textbox)
        private val proteinItemView: TextView =
            itemView.findViewById<TextView>(R.id.protine_quantity_textbox)
        private val carbItemView: TextView =
            itemView.findViewById<TextView>(R.id.carb_view_text_view)

        private val fatItemView: TextView =
            itemView.findViewById<TextView>(R.id.fat_view_text_view)

        private val detailsButton: ImageButton =
            itemView.findViewById<ImageButton>(R.id.more_details_img_button)


        fun foodNameBind(text: String) {
            foodItemView.text = "$text"
        }

        fun foodCaloriesBind(value: Int) {
            caloriesItemView.text = "Calories : ${value.toString()}"
        }

        fun foodProteinBind(value: Int) {
            proteinItemView.text = "Protein : ${value.toString()} (g)"
        }

        fun foodCarbsBind(value: Int) {
            carbItemView.text = "Carbs : ${value.toString()} (g)"
        }

        fun foodFatBind(value: Double) {
            fatItemView.text = "Fat : ${value.toString()} (g)"
        }

        fun detailFoodBind(food: Food) {
            detailsButton.setOnClickListener {
                val intent =
                    Intent(itemView.context, NutritionRepositoryShowFoodActivity::class.java)
                intent.getStringExtra(Json.encodeToString(food))
                itemView.context.startActivity(intent)
            }
        }

        companion object {
            fun create(parent: ViewGroup): FoodViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.food_recycler_item_view, parent, false)
                return FoodViewHolder(view)
            }
        }
    }

    companion object {
        private val FOOD_COMPARATOR = object : DiffUtil.ItemCallback<Food>() {
            override fun areItemsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Food, newItem: Food): Boolean {
                return oldItem == newItem
            }
        }
    }
}