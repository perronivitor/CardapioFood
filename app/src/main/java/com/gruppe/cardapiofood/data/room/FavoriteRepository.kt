package com.gruppe.cardapiofood.data.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gruppe.cardapiofood.ui.viewmodel.Meal

class FavoriteRepository(private val favoriteDAO: FavoriteDAO) {

    private val _favoriteList: LiveData<List<MealEntity>>
        get() = favoriteDAO.getAllFavoriteMeals()

    val favoriteList = Transformations.map(_favoriteList) {
        it.mapTo(arrayListOf()) { f ->
            Meal(
                title = f.title,
                imgUrl = f.imgUrl,
                isFavorite = f.isFavorite
            )
        }
    }

    suspend fun searchMealInFavoriteList(title: String) =
        favoriteDAO.searchMealInFavoriteList(title)

    suspend fun save(meal: Meal) {
        favoriteDAO.save(transformerMealInEntity(meal))
    }

    suspend fun delete(meal: Meal) {
        favoriteDAO.delete(transformerMealInEntity(meal))
    }

    private fun transformerMealInEntity(meal : Meal) : MealEntity{
        return MealEntity(
            title = meal.title,
            imgUrl = meal.imgUrl,
            isFavorite = meal.isFavorite
        )
    }

}