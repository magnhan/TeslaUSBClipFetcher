package com.example.teslausbclipfetcher

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceBuilder {
    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://teslausb-281110.ey.r.appspot.com/") // change this IP for testing by your actual machine IP
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
    val clipFetchService: RestApi = retrofit.create(RestApi::class.java)
    /*fun<T> buildService(service: Class<T>): T{
        return retrofit.create(service)
    }*/
}