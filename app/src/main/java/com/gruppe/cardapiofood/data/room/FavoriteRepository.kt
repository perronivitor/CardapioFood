package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

    val favoriteList: LiveData<List<Meal>>
        get() = favoriteDAO.getAllFavoriteMeals()

    suspend fun searchMealInFavoriteList(title: String) =
        favoriteDAO.searchMealInFavoriteList(title)

    suspend fun save(meal: Meal) {
        favoriteDAO.save(meal)
    }

    suspend fun delete(meal: Meal) {
        favoriteDAO.delete(meal)
    }

}