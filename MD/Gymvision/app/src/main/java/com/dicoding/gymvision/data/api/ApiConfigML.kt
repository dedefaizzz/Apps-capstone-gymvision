package com.dicoding.gymvision.data.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiConfigML {
    fun getApiService(): ApiServiceML {
        val loggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS) // set connection timeout
            .readTimeout(30, TimeUnit.SECONDS) // set read timeout
            .writeTimeout(30, TimeUnit.SECONDS) // set write timeout
            .build()

        val retrofit= Retrofit.Builder()
            .baseUrl("http://34.101.215.3/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ApiServiceML::class.java)
    }
}