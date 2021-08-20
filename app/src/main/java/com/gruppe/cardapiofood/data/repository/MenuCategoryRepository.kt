package com.gruppe.cardapiofood.data.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.CategoryData
import com.gruppe.cardapiofood.ui.model.RequestListCategories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

object MenuCategoryRepository {

    suspend fun getMenuCatedoryList(
        success: (categories: List<CategoryData>) -> Unit,
        failure: (t: Throwable) -> Unit
    ) {
        withContext(Dispatchers.IO) {
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
                                        Log.i("MenuCategoryRepository", list.toString())
                                        success(list.categories)
                                    }
                                }
                                else -> {
                                    "${res.code()}, ${res.message()}".let {
                                        Log.i("MenuCategoryRepository", it)
                                        failure(Exception(it))
                                    }
                                }
                            }
                        } catch (e: Exception) {
                            Log.e("MenuCategoryRepository", e.message.toString())
                            failure(Exception(e))
                        }
                    }

                    override fun onFailure(call: Call<RequestListCategories?>, t: Throwable) {
                        Log.e("MenuCategoryRepository", t.message.toString())
                        failure(t)
                    }
                })
        }
    }
}