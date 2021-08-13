package com.gruppe.cardapiofood.ui.listener

import com.gruppe.cardapiofood.ui.model.CategoryData

interface GetMenuCategoryList {

    fun onSuccess(categoryItens: List<CategoryData>)

    fun onErrorCode(errorCode: Int, message: String)

    fun onFailure(message: String)
}