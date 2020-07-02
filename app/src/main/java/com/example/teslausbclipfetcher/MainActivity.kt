package com.example.teslausbclipfetcher

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.extensions.jsonBody
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        println("klar som et egg")
        FuelManager.instance.basePath = "http://www.teslausb-281110.ey.r.appspot.com";
    }
    fun httpPostJson(view: View){
        try {
            Fuel.post(
                "json")
                .jsonBody("{ \"car_name\" : \"${editVIN.text}\" }")
                .response {result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        val clips = Gson().fromJson(String(bytes), Map::class.java)
                        print(clips)
                        var savedClips = listOf<String>()
                        var sentryClips = listOf<String>()
                        if(clips["SavedClips"] != "det er ingen savedclips til: ${editVIN.text}"){
                            savedClips = clips["SavedClips"] as List<String>
                        }
                        if(clips["SentryClips"] != "det er ingen sentry_clips til: ${editVIN.text}") {
                            sentryClips = clips["SentryClips"] as List<String>
                        }
                        for(video in savedClips){
                            println("Kaller funksjon for $video")
                            addVideoView(video)
                        }
                    }
                }
        } catch (e: Exception){
            println("ting er fucka opp ${e.message}")
        }
    }
    private fun addVideoView(url: String){
        println("Legger inn $url")
        val videoView = VideoView(this)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10,0,10,10)
        videoView.layoutParams = params
        val uri: Uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
        videoView.setVideoPath("https://tiger.cdnja.co/v/qp/QpyaV.mp4?secure=H3i0G1KyG_N8yaNwIix7lg&expires=1593678600")
        youtubeView.setVideoURI(uri)
        println("video satt")
        //videoView.start()
        val linearLayout: LinearLayout = findViewById(R.id.root_layout)
        //linearLayout.addView(videoView)
    }
    fun times(){
        //savedClips.forEach(addVideoView())
    }
}


