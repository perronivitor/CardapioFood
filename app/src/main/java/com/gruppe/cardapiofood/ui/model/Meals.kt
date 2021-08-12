package com.gruppe.cardapiofood.ui.model

import com.google.gson.annotations.SerializedName

data class Meals(
    val idMeal: String,
    val strMeal: String,
    @SerializedName("strMealThumb")
    val img: String)