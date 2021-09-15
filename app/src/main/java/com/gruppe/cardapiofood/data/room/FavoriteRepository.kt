package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import com.gruppe.cardapiofood.ui.model.Recipe
import com.gruppe.cardapiofood.ui.viewmodel.Meal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

    val mFavoriteList: LiveData<List<Meal>> = favoriteDAO.getAllFavoriteMeals()

    suspend fun searchMealInFavoriteList(title: String) =
        favoriteDAO.searchMealInFavoriteList(title)

    suspend fun save(recipe: RecipeWithIngredients) {
        favoriteDAO.save(recipe)
    }

    suspend fun delete(recipe: RecipeEntity) {
        favoriteDAO.delete(recipe)
    }

    suspend fun getRecipe(title: String) =
        withContext(Dispatchers.IO) {
            favoriteDAO.getRecipeWithIngredients(title)
        }


}