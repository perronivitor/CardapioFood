package com.gruppe.cardapiofood.ui.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.ui.model.RecipeData
import com.gruppe.cardapiofood.ui.model.RequestRecipe
import com.gruppe.cardapiofood.ui.viewmodel.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RecipeRepository {

    suspend fun getIngredients(meal : String,
                       success : (recipe : Recipe) -> Unit,
                       failure : (t : Throwable) -> Unit) {

        withContext(Dispatchers.IO) {

            RetrofitClient.service.getIngredients(meal)?.enqueue(object : Callback<RequestRecipe> {
                override fun onResponse(
                    call: Call<RequestRecipe>,
                    res: Response<RequestRecipe>
                ) {
                    try {
                        when {
                            res.isSuccessful -> {
                                res.body()?.let { list ->
                                    Log.i("RecipeRepository", "Response->$list")
                                    val recipe = Recipe().apply {
                                        with(list.meals[0]) {
                                            id = idMeal
                                            title = strMeal
                                            prepareMode = strInstructions
                                            ingredients = getIngredientsList()
                                        }
                                    }
                                    success(recipe)
                                }
                            }
                            else -> {
                                "${res.code()}, ${res.message()}".let {
                                    Log.i("RecipeRepository", it)
                                    failure(java.lang.Exception(it))
                                }
                            }
                        }

                    } catch (e: Exception) {
                        Log.e("RecipeRepository", e.message.toString())
                        failure(java.lang.Exception(e))
                    }
                }

                override fun onFailure(call: Call<RequestRecipe>, t: Throwable) {
                    Log.e("RecipeRepository", t.message.toString())
                    failure(t)
                }
            })
        }
    }
}