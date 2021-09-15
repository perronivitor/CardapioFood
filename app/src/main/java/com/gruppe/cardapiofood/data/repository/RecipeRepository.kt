package com.gruppe.cardapiofood.data.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.Resultado
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.Recipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.net.ConnectException

object RecipeRepository {

    suspend fun getIngredients(meal: String) =
        withContext(Dispatchers.IO) {
            try {
                val repo = RetrofitClient.service.getIngredients(meal)
                if (repo.isSuccessful) {
                    repo.body()?.let { list ->
                        Log.i("RecipeRepository", "Response->$list")
                        with(list.meals[0]) {
                            val recipe = Recipe(
                                title = strMeal,
                                imgUrl = img,
                                prepareMode = strInstructions,
                                isFavorite = false,
                                ingredients = getIngredientsList()
                            )
                            Resultado.Sucesso(recipe)
                        }
                    }
                } else {
                    Resultado.Erro(exception = Exception("Falha ao buscar o endereco"))
                }

            } catch (e: ConnectException) {
                e.printStackTrace()
                Resultado.Erro(exception = Exception("Falha na comunicação com API"))
            } catch (e: Exception) {
                e.printStackTrace()
                Resultado.Erro(exception = Exception("Erro desconhecido"))
            }
        }
}
