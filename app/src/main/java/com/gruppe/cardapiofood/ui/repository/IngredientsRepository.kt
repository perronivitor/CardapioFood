package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.listener.GetIngredients
import com.gruppe.cardapiofood.ui.model.RequestIngredients
import com.gruppe.cardapiofood.ui.model.RequestMeals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsRepository {

    fun getIngredients(meal: String, listener: GetIngredients) {

        RetrofitClient.service.getIngredients(meal)?.enqueue(object : Callback<RequestIngredients> {
            override fun onResponse(
                call: Call<RequestIngredients>,
                res: Response<RequestIngredients>) {
                try {
                    when {
                        res.isSuccessful -> {
                            res.body()?.let { list ->
                                Log.i("IngredientsRepository", "Response->$list")
                                listener.onSuccess(list.meals[0])
                            }
                        }
                        else -> {
                            listener.onErrorCode(res.code(), res.message())
                        }
                    }

                } catch (e: Exception) {

                }
            }

            override fun onFailure(call: Call<RequestIngredients>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }
        })
    }
}