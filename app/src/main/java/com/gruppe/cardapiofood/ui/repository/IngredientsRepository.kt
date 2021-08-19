package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.RequestIngredients
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class IngredientsRepository {


    fun getIngredients(meal: String) {

        RetrofitClient.service.getIngredients(meal)?.enqueue(object : Callback<RequestIngredients> {
            override fun onResponse(
                call: Call<RequestIngredients>,
                res: Response<RequestIngredients>) {
                try {
                    when {
                        res.isSuccessful -> {
                            res.body()?.let { list ->
                                Log.i("IngredientsRepository", "Response->$list")
                            }
                        }
                        else -> {
                        }
                    }

                } catch (e: Exception) {

                }
            }

            override fun onFailure(call: Call<RequestIngredients>, t: Throwable) {
            }
        })
    }
}