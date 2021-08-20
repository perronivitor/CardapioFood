package com.gruppe.cardapiofood.data.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.ui.model.RequestListMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

object MealsRepository {

    suspend fun getMeals(
        category: String,
        success: (meals: List<MealData>) -> Unit,
        failure: (t: Throwable) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            RetrofitClient.service.getMeals(category)?.enqueue(object : Callback<RequestListMeals> {
                override fun onResponse(
                    call: Call<RequestListMeals>,
                    res: Response<RequestListMeals>
                ) {
                    try {
                        when {
                            res.isSuccessful -> {
                                res.body()?.let { list ->
                                    Log.i("MenuCategoryRepository", list.toString())
                                    success(list.meals)
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

                override fun onFailure(call: Call<RequestListMeals>, t: Throwable) {
                    Log.e("MenuCategoryRepository", t.message.toString())
                    failure(t)
                }
            })
        }
    }

}