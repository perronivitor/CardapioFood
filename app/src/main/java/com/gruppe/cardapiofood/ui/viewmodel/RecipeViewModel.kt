package com.gruppe.cardapiofood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gruppe.cardapiofood.data.repository.RecipeRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel : ViewModel() {

    private var _mMealIngredientList = MutableLiveData<Recipe>()
    val mMealIngredientItemList get() = _mMealIngredientList

    var error = MutableLiveData<String?>(null)

    var _mProgressBar = MutableLiveData<Boolean>(null)

     fun getIngredients(meal: String) {
         launchDataLoad {
             RecipeRepository.getIngredients(meal,
                 { recipe -> _mMealIngredientList.postValue(recipe) },
                 { throwable -> error.postValue(throwable.toString()) })
         }
    }

    fun setIsCheckIngredient(position: Int) {
        try {
            val recipe = mMealIngredientItemList.value
            recipe!!.ingredients[position].isCheck = !recipe.ingredients[position].isCheck
            _mMealIngredientList.postValue(recipe!!)
        } catch (e: Exception) {
            Log.e("RecipeViewModel", e.message.toString())
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