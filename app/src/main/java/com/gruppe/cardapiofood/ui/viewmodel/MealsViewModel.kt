package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.data.repository.MealsRepository
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {

    var _error = MutableLiveData<String?>(null)

    var _mProgressBar = MutableLiveData<Boolean>(null)

    private val _mMealsList = MutableLiveData<List<MealData>>()
    val mMealItemList = Transformations.map(_mMealsList) {
        it.mapTo(arrayListOf()) { m ->
            Meal(
                title = m.strMeal,
                imgUrl = m.imgUrl
            )
        }
    }

    fun getMealsList(category: String) {
        launchDataLoad {
            MealsRepository.getMeals(
                category,
                { meals ->
                    _mMealsList.postValue(meals)
                }, { throwable ->

                })
        }
    }

    fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                _mProgressBar.postValue(true)
                block()
            } catch (e: Exception) {
                //Como tratar Exception?
            } finally {
                _mProgressBar.postValue(false)
            }
        }
    }
}