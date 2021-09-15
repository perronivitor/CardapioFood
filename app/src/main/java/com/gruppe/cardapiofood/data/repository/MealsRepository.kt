package com.gruppe.cardapiofood.data.repository

import android.util.Log
import com.gruppe.cardapiofood.retrofit.Resultado
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import com.gruppe.cardapiofood.ui.model.MealData
import com.gruppe.cardapiofood.ui.model.RequestListMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import java.net.ConnectException

object MealsRepository {

    suspend fun getMeals(category: String) =
        withContext(Dispatchers.IO) {
            try {
                val repo = RetrofitClient.service.getMeals(category)
                if (repo.isSuccessful) {
                    Resultado.Sucesso(dado = repo.body())
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