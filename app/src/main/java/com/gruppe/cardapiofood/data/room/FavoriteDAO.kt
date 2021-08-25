package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.gruppe.cardapiofood.ui.viewmodel.Meal

@Dao
interface FavoriteDAO {

    @Insert
    suspend fun save (meal : MealEntity)

    @Delete
    suspend fun delete (meal : MealEntity)

    @Query("SELECT id FROM table_favorite_meals WHERE title = :title")
    suspend fun searchMealInFavoriteList(title: String) : Int

    @Query("SELECT * FROM table_favorite_meals")
    fun getAllFavoriteMeals(): LiveData<List<MealEntity>>

}