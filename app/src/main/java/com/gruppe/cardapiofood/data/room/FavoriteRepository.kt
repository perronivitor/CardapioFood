package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

   val mFavoriteList: LiveData<List<Meal>> =favoriteDAO.getAllFavoriteMeals()

    suspend fun searchMealInFavoriteList(title: String)  =
        favoriteDAO.searchMealInFavoriteList(title)

    suspend fun save(recipe: RecipeEntity) {
        favoriteDAO.save(recipe)
    }

    suspend fun delete(recipe : RecipeEntity) {
        favoriteDAO.delete(recipe)
    }

    fun getRecipe(title: String) = favoriteDAO.getRecipe(title)


}