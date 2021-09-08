package com.gruppe.cardapiofood.retrofit

import com.gruppe.cardapiofood.ui.model.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface Service {

    @GET("categories.php")
    suspend fun getMenuCategoryList(): Call<RequestListCategories?>

    @GET("filter.php")
    suspend fun getMeals(@Query ("c") category: String ) : Call<RequestListMeals>?

    @GET("search.php")
    suspend fun getIngredients(@Query ("s") meal: String ) : Call<RequestRecipe>?

}