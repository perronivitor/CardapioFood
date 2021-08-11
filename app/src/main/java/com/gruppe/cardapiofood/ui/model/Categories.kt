package com.gruppe.cardapiofood.ui.model

import com.google.gson.annotations.SerializedName


data class Categories(
    var idCategory: String = "",
    var strCategory: String = "",
    @SerializedName("strCategoryThumb")
    var img: String = "",
)