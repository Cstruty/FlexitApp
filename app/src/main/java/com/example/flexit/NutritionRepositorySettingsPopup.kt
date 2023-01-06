package com.example.flexit

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class NutritionRepositorySettingsPopup : AppCompatActivity() {

    private var resetDatabaseButton: Button? = null
    private var keepDatabaseButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.nutrition_repo_check_reset_popup_window)

        resetDatabaseButton = findViewById<Button>(R.id.reset_database_button) as Button
        keepDatabaseButton = findViewById<Button>(R.id.keep_database_same_button) as Button

        resetDatabaseButton!!.setOnClickListener{
            sendResult(1)
        }

        keepDatabaseButton!!.setOnClickListener{
            sendResult(0)
        }

    }

    private fun sendResult(response : Int) {
        val replyIntent = Intent()
        setResult(Activity.RESULT_OK, replyIntent)
        replyIntent.putExtra(EXTRA_REPLY, response)
        finish()
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.wordlistsql.REPLY"
    }
}