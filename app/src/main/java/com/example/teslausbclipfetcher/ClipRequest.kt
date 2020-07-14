package com.example.teslausbclipfetcher

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

object ClipRequest {
    fun postRequest(car: Car) {
        println("Fetching clips for ${car.vin}")
        try {
            Fuel.post(
                "http://www.teslausb-281110.ey.r.appspot.com/json"
            )
                .jsonBody(
                    "{  \"car_name\" : \"${car.vin}\"  }"
                )
                .response { result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        val clips = Gson().fromJson(String(bytes), Map::class.java)
                        @Suppress("UNCHECKED_CAST")
                        val savedClips = clips["SavedClips"] as List<String>
                        @Suppress("UNCHECKED_CAST")
        val sentryClips = clips["SentryClips"] as List<String>
            car.setClips(savedClips, sentryClips)
        }
    }
} catch (e: Exception) {
            println(e.message)
        }
    }
}