package com.example.teslausbclipfetcher

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

/* class RestApiService {
    fun getClips(clips: Clips, onResult: (Clips?) -> Unit){
        val retrofit = ServiceBuilder.buildService(RestApi::class.java)
        retrofit.getClips(clips).enqueue(
            object : Callback<Clips> {
                override fun onFailure(call: Call<Clips>, t: Throwable) {
                    onResult(null)
                }
                override fun onResponse( call: Call<Clips>, response: Response<Clips>) {
                    val requestedClips = response.body()
                    onResult(requestedClips)
                }
            }
        )
    }
}*/