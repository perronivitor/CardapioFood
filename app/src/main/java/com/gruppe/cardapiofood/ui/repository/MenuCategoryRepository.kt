package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.listener.GetMenuCategoryList
import com.gruppe.cardapiofood.ui.model.Categories
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class MenuCategoryRepository {

    fun getMenuCatedoryList(listener: GetMenuCategoryList) {

        RetrofitClient.service.getMenuCategoryList()
            .enqueue(object : Callback<MutableList<Categories?>?> {
                override fun onResponse(
                    call: Call<MutableList<Categories?>?>,
                    res: Response<MutableList<Categories?>?>
                ) {
                    try {
                        when {
                            res.isSuccessful -> {
                                res.body()?.let { list ->
                                  listener.onSuccess(list)
                                }
                            }
                            else -> {
                                listener.onErrorCode(res.code(), res.message())
                            }
                        }

                    } catch (e: Exception) {
                        Log.e("MenuCategoryRepository", e.message.toString())
                    }
                }

                override fun onFailure(call: Call<MutableList<Categories?>?>, t: Throwable) {
                    listener.onFailure(t.message.toString())
                }
            })
    }
}