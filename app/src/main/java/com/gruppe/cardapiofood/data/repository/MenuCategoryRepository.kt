package com.gruppe.cardapiofood.data.repository

import com.gruppe.cardapiofood.retrofit.RetrofitClient

object MenuCategoryRepository {

    suspend fun getMenuCatedoryList() = RetrofitClient.service.getMenuCategoryList().categories

}