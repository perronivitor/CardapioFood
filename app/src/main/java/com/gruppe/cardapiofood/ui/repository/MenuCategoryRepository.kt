package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.listener.GetMenuCategoryList
import com.gruppe.cardapiofood.ui.model.Categories
import com.gruppe.cardapiofood.ui.model.RequestListCategories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MenuCategoryRepository {

    fun getMenuCatedoryList(listener: GetMenuCategoryList) {

        RetrofitClient.service.getMenuCategoryList()
            .enqueue(object : Callback<RequestListCategories?> {
                override fun onResponse(
                    call: Call<RequestListCategories?>,
                    res: Response<RequestListCategories?>
                ) {
                    try {
                        when {
                            res.isSuccessful -> {
                                res.body()?.let { list ->
                                    Log.i("MenuCategoryRepository",list.toString())
                                    val categories = mutableListOf<Categories?>()
                                    categories.addAll(list.categories)
                                    listener.onSuccess(categories)
                                }
                            }
                            else -> {
                                Log.i("MenuCategoryRepository","${res.code()}, ${res.message()}")
                                listener.onErrorCode(res.code(), res.message())
                            }
                        }

                    } catch (e: Exception) {
                        Log.e("MenuCategoryRepository", e.message.toString())
                    }
                }

                override fun onFailure(call: Call<RequestListCategories?>, t: Throwable) {
                    Log.e("MenuCategoryRepository", t.message.toString())
                    listener.onFailure(t.message.toString())
                }
            })
    }
}