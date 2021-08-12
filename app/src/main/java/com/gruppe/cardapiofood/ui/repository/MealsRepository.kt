package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.listener.GetMeals
import com.gruppe.cardapiofood.ui.model.Meals
import com.gruppe.cardapiofood.ui.model.RequestMeals
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealsRepository {

    fun getMeals(meal: String, listener: GetMeals) {

        RetrofitClient.service.getMeals(meal)?.enqueue(object : Callback<RequestMeals>{
            override fun onResponse(call: Call<RequestMeals>, res: Response<RequestMeals>) {
                try{
                    when{
                        res.isSuccessful->{
                            res.body()?.let{list->
                                Log.i("MealsRepository","Response -> $list")
                                val meals = mutableListOf<Meals>()
                                meals.addAll(list.meals)
                                listener.onSuccess(meals)
                            }
                        }
                        else->{
                            listener.onErrorCode(res.code(),res.errorBody().toString())
                        }
                    }
                }catch (e : Exception){
                    Log.e("MealsRepository",e.message.toString())
                }
            }

            override fun onFailure(call: Call<RequestMeals>, t: Throwable) {
                Log.e("MealsRepo_onFailure",t.message.toString())
               listener.onFailure(t.message.toString())
            }
        })
    }

}