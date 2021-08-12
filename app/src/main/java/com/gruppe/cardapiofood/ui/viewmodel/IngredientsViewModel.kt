package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.listener.GetIngredients
import com.gruppe.cardapiofood.ui.model.Ingredients
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.model.RequestIngredients
import com.gruppe.cardapiofood.ui.repository.IngredientsRepository

class IngredientsViewModel : ViewModel() {

    private val repository = IngredientsRepository()

    var mIngredients = MutableLiveData<Ingredients>()

    fun getIngredients(meal : String){
        repository.getIngredients(meal, object : GetIngredients{
            override fun onSuccess(ingredientItens: Ingredients) {
                mIngredients.postValue(ingredientItens)
            }

            override fun onErrorCode(errorCode: Int, message: String) {
                TODO("Not yet implemented")
            }

            override fun onFailure(message: String) {
                TODO("Not yet implemented")
            }
        })
    }
}