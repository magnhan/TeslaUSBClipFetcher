package com.example.teslausbclipfetcher

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody

class MainActivity : AppCompatActivity() {

    var clips: String = ""

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
                .response {result -> clips = result.get().content
                }
        } catch (e: Exception){
            println("ting er fucka opp ${e.message}")
        }
        print(clips)
    }
}


