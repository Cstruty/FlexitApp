package com.example.flexit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flexit.nutrition_repository.FoodViewModel
import com.example.flexit.nutrition_repository.FoodViewModelFactory
import com.example.flexit.nutrition_repository.food.Food
import com.google.android.material.textfield.TextInputEditText
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json


class NutritionRepositoryActivity : AppCompatActivity() {
    private val TAG: String = "NutritionRepoActiv"
    private var searchButton: Button? = null
    private var settingsButton: Button? = null
    private var addFoodButton: Button? = null
    private var foodNameSearchTextBox: TextInputEditText? = null
    private val adapter: FoodListAdapter = FoodListAdapter()

    private val newFoodActivityRequestCode = 1
    private val foodViewModel: FoodViewModel by viewModels {
        FoodViewModelFactory((application as FlexitApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutrition_repository)
        addFoodButton = findViewById<Button>(R.id.add_food_button) as Button
        searchButton = findViewById<Button>(R.id.search_button) as Button
        foodNameSearchTextBox =
            findViewById<TextInputEditText>(R.id.food_name_search) as TextInputEditText
        settingsButton = findViewById<Button>(R.id.settings_button) as Button
        val foodRecycler = findViewById<RecyclerView>(R.id.food_recycler)


        foodRecycler.adapter = adapter
        foodRecycler.layoutManager = LinearLayoutManager(this)

        addFoodButton!!.setOnClickListener {
            val intent = Intent(applicationContext, AddFoodActivity::class.java)
            startActivityForResult(intent, newFoodActivityRequestCode)
        }

        searchButton!!.setOnClickListener {
            val searchbarText: String = foodNameSearchTextBox!!.editableText.toString()
            getItemsFromDb(searchbarText)
        }

        settingsButton!!.setOnClickListener {
//            setContentView(R.layout.fragment_nutrition_settings)
//            resetList()
            val intent =
                Intent(applicationContext, NutiritionRepositorySettingsActivity::class.java)
            startActivity(intent)
        }

        resetList()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newFoodActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(AddFoodActivity.EXTRA_REPLY)?.let { reply ->
                val food: Food = Json.decodeFromString<Food>(reply)
                foodViewModel.insert(food)
            }
        } else {
            Toast.makeText(
                applicationContext, "Canceling addition of food", Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun getItemsFromDb(foodName: String) {
        foodViewModel.search(foodName).observe(this) { foods ->
            foods.let {
                adapter.submitList(it)
                Log.d(TAG, foods.toString())
            }
        }
    }

    private fun resetList() {
        foodViewModel.getAllFoods().observe(this) { foods ->
            foods.let {
                adapter.submitList(it)
                Log.d(TAG, foods.toString())
            }
        }
    }
}