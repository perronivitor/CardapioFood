package com.gruppe.cardapiofood.ui.listener

import com.gruppe.cardapiofood.ui.model.Categories

interface GetMenuCategoryList {

    fun onSuccess(categoryItens: MutableList<Categories?>)

    fun onErrorCode(errorCode :Int, message : String)

    fun onFailure(message : String)
}