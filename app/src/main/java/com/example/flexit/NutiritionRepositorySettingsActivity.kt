package com.example.flexit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.flexit.nutrition_repository.FoodViewModel
import com.example.flexit.nutrition_repository.FoodViewModelFactory

class NutiritionRepositorySettingsActivity : AppCompatActivity() {
    private var resetDatabaseButton: Button? = null
    private var finishSettingsButton: Button? = null


    private val checkWipeData = 1
    private val foodViewModel: FoodViewModel by viewModels {
        FoodViewModelFactory((application as FlexitApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nutirition_repository_settings)


        resetDatabaseButton = findViewById<Button>(R.id.reset_repo_button) as Button
        finishSettingsButton = findViewById<Button>(R.id.nutritent_repo_finish_button) as Button

        resetDatabaseButton!!.setOnClickListener {
            val intent = Intent(applicationContext, NutritionRepositorySettingsPopup::class.java)
            startActivityForResult(intent, checkWipeData)
        }

        finishSettingsButton!!.setOnClickListener {
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == checkWipeData && resultCode == Activity.RESULT_OK) {
            data?.getIntExtra(NutritionRepositorySettingsPopup.EXTRA_REPLY, 0)?.let {reply ->
                if (reply == 1) {
                    foodViewModel.clearAllFoods()
                }
            }
        }
    }


}