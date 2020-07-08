package com.example.teslausbclipfetcher

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson

class ClipRequest(savedClips: List<String>, sentryClips: List<String>)

fun postRequest(vin: String): ClipRequest{
    var bytes = byteArrayOf()
    var savedClips: List<String> = listOf()
    var sentryClips: List<String> = listOf()
    try {
        Fuel.post(
            "http://www.teslausb-281110.ey.r.appspot.com/json"
        )
            .jsonBody(
                "{  \"car_name\" : \"$vin\"  }"
            )
            .response { result ->
                val (bytes, error) = result
                if (bytes != null) {
                    val clips = Gson().fromJson(String(bytes), Map::class.java)
                    @Suppress("UNCHECKED_CAST")
                    savedClips = clips["SavedClips"] as List<String>
                    @Suppress("UNCHECKED_CAST")
                    sentryClips = clips["SentryClips"] as List<String>
                }
            }
        }
    catch (e: Exception) {
        println(e.message)
    }
    return ClipRequest(savedClips, sentryClips)
}