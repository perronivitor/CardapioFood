package com.gruppe.cardapiofood.data.repository

import android.util.Log
import com.gruppe.cardapiofood.data.room.RecipeEntity
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.RequestRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RecipeRepository {

    suspend fun getIngredients(
        meal: String,
        success: (recipe: RecipeEntity) -> Unit,
        failure: (t: Throwable) -> Unit,
    ) {

        withContext(Dispatchers.IO) {

            RetrofitClient.service.getIngredients(meal)?.enqueue(object : Callback<RequestRecipe> {
                override fun onResponse(
                    call: Call<RequestRecipe>,
                    res: Response<RequestRecipe>,
                ) {
                    try {
                        when {
                            res.isSuccessful -> {
                                res.body()?.let { list ->
                                    Log.i("RecipeRepository", "Response->$list")
                                    with(list.meals[0]) {
                                        val recipe = RecipeEntity(
                                            id = idMeal.toLong(),
                                            title = strMeal,
                                            imgUrl = img,
                                            prepareMode = strInstructions,
                                            ingredients = getIngredientsList(),
                                            isFavorite = false
                                        )
                                        success(recipe)
                                    }
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