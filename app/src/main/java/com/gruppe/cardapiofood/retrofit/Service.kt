package com.gruppe.cardapiofood.retrofit

import com.gruppe.cardapiofood.ui.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {

    @GET("categories.php")
    fun getMenuCategoryList(): Call<RequestListCategories?>

    @GET("filter.php")
    fun getMeals(@Query ("c") meals: String ) : Call<RequestMeals>?

    @GET("search.php")
    fun getIngredients(@Query ("s") meals: String ) : Call<RequestIngredients>?

}