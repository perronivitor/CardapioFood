package com.gruppe.cardapiofood.ui.listener

import com.gruppe.cardapiofood.ui.model.Meals

interface GetMeals {

    fun onSuccess(mealsItens: MutableList<Meals>)

    fun onErrorCode(errorCode :Int, message : String)

    fun onFailure(message : String)

}
