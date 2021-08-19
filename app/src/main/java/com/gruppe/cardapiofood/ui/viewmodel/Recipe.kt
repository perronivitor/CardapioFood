package com.gruppe.cardapiofood.ui.viewmodel

import com.gruppe.cardapiofood.ui.model.Ingredient

data class Recipe(
    var id: String = "",
    var title: String = "",
    var prepareMode : String ="",
    var ingredients : List<Ingredient> = listOf()
)