package com.gruppe.cardapiofood.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.repository.RecipeRepository
import java.lang.Exception

class RecipeViewModel : ViewModel() {

    private var _mMealIngredientList = MutableLiveData<Recipe>()
    val mMealIngredientItemList get() = _mMealIngredientList

    fun getIngredients(meal: String) {
        RecipeRepository.getIngredients(meal,
            { recipe -> _mMealIngredientList.postValue(recipe) },
            { throwable -> })
    }

    fun setIsCheckIngredient(position: Int) {
        try {
            val recipe = _mMealIngredientList.value
            recipe!!.ingredients[position].isCheck = !recipe.ingredients[position].isCheck
            _mMealIngredientList.postValue(recipe!!)
        } catch (e: Exception) {
            Log.e("RecipeViewModel", e.message.toString())
        }
    }
}