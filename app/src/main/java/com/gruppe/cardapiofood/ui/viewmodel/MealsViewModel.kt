package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.listener.GetMeals
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.repository.MealsRepository

class MealsViewModel : ViewModel() {

    private val repository = MealsRepository()

    var mMealsList = MutableLiveData<MutableList<Meals>>()

    var mMealCurrent = MutableLiveData<Meals>()

    fun getMeals(meal: String){
        repository.getMeals(meal,object : GetMeals{
            override fun onSuccess(mealsItens: MutableList<Meals>) {
                mMealsList.postValue(mealsItens)

            }

            override fun onErrorCode(errorCode: Int, message: String) {

            }

            override fun onFailure(message: String) {

            }
        })
    }
}