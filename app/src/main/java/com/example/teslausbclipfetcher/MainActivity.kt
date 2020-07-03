package com.example.teslausbclipfetcher

// import android.net.Uri
import android.os.Bundle
import android.view.View
// import android.view.ViewGroup
// import android.widget.LinearLayout
import android.widget.TextView
// import android.widget.VideoView
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
        FuelManager.instance.basePath = "http://www.teslausb-281110.ey.r.appspot.com"
    }

    fun httpPostJson(view: View) {
        // println(editVIN.text)
        try {
            Fuel.post(
                "json"
            )
                .jsonBody(
                    "{  \"car_name\" : \"${editVIN.text}\"  }"
                )
                .response { result ->
                    val (bytes, error) = result
                    if (bytes != null) {
                        val clips = Gson().fromJson(String(bytes), Map::class.java)
                        // print(clips)
                        @Suppress("UNCHECKED_CAST")
                        val savedClips = clips["SentryClips"] as List<String>
                        @Suppress("UNCHECKED_CAST")
                        val sentryClips = clips["SentryClips"] as List<String>
                        if (savedClips.isNotEmpty()) {
                            for (video in savedClips) {
                                // println("Kaller funksjon for $video")
                                addVideoView(video)
                            }
                        }
                        if (sentryClips.isNotEmpty()) {
                            for (video in sentryClips) {
                                // ("Kaller funksjon for $video")
                                addVideoView(video)
                            }
                        }
                    }
                }
        } catch (e: Exception) {
            println(e.message)
        }
    }

    private fun addVideoView(url: String) {
        val output: TextView = findViewById(R.id.outputText)
        var newText: String = "${output.text}" + "\n$url"
        if (output.text == "") {
            newText = url
        }
        output.text = newText
        // println(url)
        /*val videoView = VideoView(this)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 0, 10, 10)
        videoView.layoutParams = params
        val uri: Uri = Uri.parse("http://techslides.com/demos/sample-videos/small.mp4")
        videoView.setVideoPath("https://tiger.cdnja.co/v/qp/QpyaV.mp4?secure=H3i0G1KyG_N8yaNwIix7lg&expires=1593678600")
        val youtube: VideoView = findViewById(R.id.youtubeView)
        youtube.setVideoURI(uri)
        println("video satt")
        // videoView.start()
        val linearLayout: LinearLayout = findViewById(R.id.root_layout)
        // linearLayout.addView(videoView)*/
    }
}
