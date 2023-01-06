package com.example.flexit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flexit.nutrition_repository.food.Food
import com.google.android.material.textfield.TextInputEditText
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AddFoodActivity : AppCompatActivity() {
    private var addButton: Button? = null
    private var foodNameTextbox: TextInputEditText? = null
    private var foodCaloreTextBox: TextInputEditText? = null
    private var foodProteinTextBox: TextInputEditText? = null
    private var foodCarbTextBox: TextInputEditText? = null
    private var foodFatTextBox: TextInputEditText? = null

//    private var foodRepo: NutritionRepository? = null
//    private var foodDatabase: NutritionRepositoryLocalDB = null
//    private var foodRepo = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)
//        foodRepo = NutrientRepositoryLocalAPI()
        addButton = findViewById<View>(R.id.add_button) as Button
        foodNameTextbox = findViewById<TextInputEditText>(R.id.editTextTextFoodName) as TextInputEditText
        foodCaloreTextBox =
            findViewById<TextInputEditText>(R.id.calories_text_input) as TextInputEditText
        foodProteinTextBox =
            findViewById<TextInputEditText>(R.id.protein_input_textbox) as TextInputEditText
        foodCarbTextBox =
            findViewById<TextInputEditText>(R.id.carb_input_textbox) as TextInputEditText
        foodFatTextBox =
            findViewById<TextInputEditText>(R.id.fat_input_textbox) as TextInputEditText
        addButton!!.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(foodNameTextbox!!.editableText)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
//                val foodName = foodNameTextbox!!.text.toString()
                var food: Food? = null
                try {
                    food = parseFood()
                    val foodJson:String = Json.encodeToString(food)
                    setResult(Activity.RESULT_OK, replyIntent)
                    replyIntent.putExtra(EXTRA_REPLY, foodJson)
                    finish()
                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        applicationContext, "Incorrect Nutrition Value", Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun getFoodNameTextboxText(): String {
        return foodNameTextbox?.text.toString()
    }

    private fun getCalorieTextboxValue(): Int {
        val intString:String = foodCaloreTextBox?.editableText.toString()
        if (intString == null) {
            throw NumberFormatException("Calorie")
        }
        return intString.toInt()
    }

    private fun getProteinTextboxValue(): Int {
        val intString:String = foodProteinTextBox?.editableText.toString()
        if (intString == null) {
            throw NumberFormatException()
        }
        return intString.toInt()
    }

    private fun getCarbTextboxValue(): Int {
        val intString:String = foodCarbTextBox?.editableText.toString()
        if (intString == null) {
            throw NumberFormatException()
        }
        return intString.toInt()
    }

    private fun getFatTextboxValue(): Double {
        val intString:String = foodCarbTextBox?.editableText.toString()
        if (intString == null) {
            throw NumberFormatException()
        }
        return intString.toDouble()
    }

    private fun parseFood() : Food {
        val foodName: String = getFoodNameTextboxText()
        val calories: Int = getCalorieTextboxValue()
        val protein: Int = getProteinTextboxValue()
        val carbs: Int  = getCarbTextboxValue()
        val fat: Double = getFatTextboxValue()
        return Food(foodName, calories, protein, carbs, fat)
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}