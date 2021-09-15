package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.data.repository.MealsRepository
import com.gruppe.cardapiofood.retrofit.Resultado
import kotlinx.coroutines.launch

class MealsViewModel : ViewModel() {

    var error = MutableLiveData<String?>(null)

    var mProgressBar = MutableLiveData<Boolean>(null)

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
            when(val repo = MealsRepository.getMeals(category)){
                is Resultado.Sucesso ->{
                    repo.dado?.let { m->
                        _mMealsList.postValue(m.meals)
                    }
                }
                is Resultado.Erro ->{
                    error.postValue(repo.exception.message)
                }
            }
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                mProgressBar.postValue(true)
                block()
            } catch (e: Exception) {
                //Como tratar Exception?
            } finally {
                mProgressBar.postValue(false)
            }
        }
    }
}