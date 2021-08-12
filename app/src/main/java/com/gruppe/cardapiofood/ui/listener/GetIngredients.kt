package com.gruppe.cardapiofood.ui.listener

import com.gruppe.cardapiofood.ui.model.Ingredients
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.model.RequestIngredients
import com.gruppe.cardapiofood.ui.model.RequestMeals

interface GetIngredients {

    fun onSuccess(ingredientItens: Ingredients)

    fun onErrorCode(errorCode :Int, message : String)

    fun onFailure(message : String)
}
