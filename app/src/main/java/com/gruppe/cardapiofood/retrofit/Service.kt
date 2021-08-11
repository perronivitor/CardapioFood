package com.gruppe.cardapiofood.retrofit

import com.gruppe.cardapiofood.ui.model.Categories
import retrofit2.Call
import retrofit2.http.GET


interface Service {

    @GET("categories.php")
    fun getMenuCategoryList(): Call<MutableList<Categories?>?>
}