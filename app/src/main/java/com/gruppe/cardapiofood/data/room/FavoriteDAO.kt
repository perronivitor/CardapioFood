package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gruppe.cardapiofood.ui.model.Recipe
import com.gruppe.cardapiofood.ui.viewmodel.Meal

@Dao
interface FavoriteDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save (recipe : RecipeWithIngredients)

    @Delete
    suspend fun delete (recipe : RecipeEntity)

    @Query("SELECT COUNT(*) FROM table_favorite_recipe WHERE title = :title")
    suspend fun searchMealInFavoriteList(title: String) : Int

    @Query("SELECT * FROM table_favorite_recipe")
    fun getAllFavoriteMeals(): LiveData<List<Meal>>

    @Query("SELECT * FROM table_favorite_recipe WHERE title= :title")
    fun getRecipe(title : String) : RecipeEntity

    @Transaction
    @Query("SELECT * FROM table_favorite_recipe WHERE title =:title ")
    suspend fun getRecipeWithIngredients(title : String): List<RecipeWithIngredients>
}