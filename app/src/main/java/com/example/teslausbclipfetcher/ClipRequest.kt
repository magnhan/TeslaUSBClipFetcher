package com.example.teslausbclipfetcher

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.github.kittinunf.result.Result
import com.google.gson.Gson

class ClipRequest(val savedClips: List<String> = listOf(), val sentryClips: List<String> = listOf())

fun postRequest(vin: String): ClipRequest{
    var savedClips: List<String> = listOf()
    var sentryClips: List<String> = listOf()
    
    val (request, response, result) =
        Fuel.post(
            "http://www.teslausb-281110.ey.r.appspot.com/json")
        .jsonBody(
            "{  \"car_name\" : \"$vin\"  }"
        )
        .response()

    when(result){
        is Result.Failure -> {
            println(result.getException())
        }
        is Result.Success -> {
            println("resultat: ${result.get()}")
            /*val (bytes, error) = result
            println(result)
            if (bytes != null) {
                val clips = Gson().fromJson(String(bytes), Map::class.java)
                @Suppress("UNCHECKED_CAST")
                savedClips = clips["SavedClips"] as List<String>
                @Suppress("UNCHECKED_CAST")
                sentryClips = clips["SentryClips"] as List<String>
                println("inni try $sentryClips")
                println("inni try $clips")
        }*/
        }
    }
    println("før retur: $sentryClips")
    return ClipRequest(savedClips, sentryClips)
}