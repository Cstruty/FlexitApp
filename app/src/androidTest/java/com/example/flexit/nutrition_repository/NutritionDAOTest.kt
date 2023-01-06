package com.example.flexit.nutrition_repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.example.flexit.nutrition_repository.food.Food
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class NutritionDAOTest {
    private lateinit var database: NutritionRepositoryLocalDB
    private lateinit var dao: NutritionDAO
    private val pepperFood = Food("Pepper", 100, 0, 3, 0.0)
    private val friedChickenFood = Food("KFC", 1000, 30, 50, 20.0)
    private val pieFood = Food("Pie Flavored Pie", 1000, 2, 100, 50.0)


    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database =
            Room.inMemoryDatabaseBuilder(context, NutritionRepositoryLocalDB::class.java).build()
        dao = database.foodDAO()
        dao.insertFood(pepperFood)
        dao.insertFood(friedChickenFood)
        dao.insertFood(pieFood)
    }

    @After
    fun clearDb() = runBlocking {
        database.close()
    }

    @Test
    fun testGetAllFoods() = runBlocking {
        val foodList = dao.getAllFoods().first()
        val size = foodList.size
        assertEquals(size, 3)

        assertTrue(pepperFood in foodList)
        assertTrue(friedChickenFood in foodList)
        assertTrue(pieFood in foodList)
    }

    @Test
    fun testInsertFood() = runBlocking {
        val otherChickenFood = Food("Buttered Chicken", 900, 20, 20, 40.0)
        dao.insertFood(otherChickenFood)

        val foodList = dao.getAllFoods().first()
        assertEquals(foodList.size, 4)

        assertEquals(foodList[0], otherChickenFood)
    }

    // TODO: Find out why this test is failing here vs. working in the context of the program

    @Test
    fun testSearchFoods() = runBlocking {
        val pieSearchKey = "Pepper"
        val foodList = dao.searchFoods(pieSearchKey).first()
        assertEquals(1, foodList.size)

        assertEquals(pepperFood, foodList[0])
    }

    @Test
    fun testBlankSearchFoods() = runBlocking {
        val blankInputKey = ""
        val resetFoodList = dao.searchFoods(blankInputKey).first()
        assertEquals(0, resetFoodList.size)
    }

    @Test
    fun testDeleteAllFoods() = runBlocking {
        dao.deleteAll()
        val foodList = dao.getAllFoods().first()

        assertEquals(0, foodList.size)
    }

}