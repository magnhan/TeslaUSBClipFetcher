package com.example.teslausbclipfetcher

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    //lateinit var clips: ByteArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FuelManager.instance.basePath = "http://www.red-freedom-269219.ew.r.appspot.com";
    }
    fun httpPostJson(view: View){
        try {
            Fuel.post(
                "json")
                .jsonBody("{ \"car_name\" : \"Nabobil\" }")
                .response {result ->
                    //val gson: Gson
                    val (bytes, error) = result
                    if (bytes != null) {
                        val clips = Gson().fromJson(String(bytes), Map::class.java)
                        val savedClips = clips["SavedClips"]
                        val sentryClips = clips["SentryClips"]
                        println(sentryClips)
                        println("[response bytes] ${String(bytes)}")
                    }
                }
        } catch (e: Exception){
            println("ting er fucka opp ${e.message}")
        }
        //print(clips)
    }
}


