package com.gruppe.cardapiofood.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gruppe.cardapiofood.ui.model.IngredientsData
import com.gruppe.cardapiofood.ui.repository.IngredientsRepository

class IngredientsViewModel : ViewModel() {

    private val repository = IngredientsRepository()

    var mIngredients = MutableLiveData<IngredientsData>()

    fun getIngredients(meal : String){
        repository.getIngredients(meal)
    }
}