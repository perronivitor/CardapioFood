package com.gruppe.cardapiofood.retrofit


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    private lateinit var retrofit: Retrofit
    private val BASE_URL = "www.themealdb.com/api/json/v1/1/"

    //Função de construção Retrofit
    private fun getRetrofitInstance() :Retrofit{
        val httpClient = OkHttpClient.Builder()
            .readTimeout(300, TimeUnit.SECONDS)
            .connectTimeout(300, TimeUnit.SECONDS)
        if (!::retrofit.isInitialized){
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()
        }
        return retrofit
    }

    //Instancia do Retrofit
    val service: Service by lazy {
        getRetrofitInstance().create(Service::class.java)
    }

}