package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.ui.repository.MealsRepository

class MealsViewModel : ViewModel() {

    private val _mMealsList = MutableLiveData<List<MealData>>()
    val mMealItemList = Transformations.map(_mMealsList){
        it.mapTo(arrayListOf()){ m ->
            Meal(
                id = m.idMeal,
                title = m.strMeal,
                imgUrl = m.imgUrl
            )
        }
    }

    fun getMealsList(category: String){
        MealsRepository.getMeals(
            category,
            { meals -> _mMealsList.postValue(meals)
            },{throwable ->

            })
    }

}