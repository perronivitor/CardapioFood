package com.gruppe.cardapiofood.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.gruppe.cardapiofood.retrofit.Resultado
import com.gruppe.cardapiofood.retrofit.RetrofitClient
import java.net.ConnectException

object MenuCategoryRepository {

    fun getMenuCatedoryList() = liveData {
        try {
            Log.i("MenuVM", "getMenuCategoryList: Antes Resposta ")
            val resposta = RetrofitClient.service.getMenuCategoryList()
            Log.i("MenuVM", "getMenuCategoryList: $resposta ")
            if (resposta.isSuccessful) {
                emit(Resultado.Sucesso(dado = resposta.body()?.categories))
            } else {
                emit(Resultado.Erro(exception = Exception("Falha ao buscar o endereco")))
            }
        } catch (e: ConnectException) {
            e.printStackTrace()
            emit(Resultado.Erro(exception = Exception("Falha na comunicação com API")))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(Resultado.Erro(exception =Exception("Erro desconhecido")))
        }
    }


}