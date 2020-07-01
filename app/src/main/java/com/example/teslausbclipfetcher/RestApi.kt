package com.example.teslausbclipfetcher

import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.Headers
import retrofit2.http.POST

interface RestApi {

    //@Headers("Content-Type: application/json")
    @POST("/json")
    fun getClips(@Query("car_name") carName: String): Call<Clips>
}