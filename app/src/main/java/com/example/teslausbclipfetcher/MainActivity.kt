package com.example.teslausbclipfetcher

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.kittinunf.fuel.core.FuelManager
import wseemann.media.FFmpegMediaMetadataRetriever


class MainActivity : AppCompatActivity() {

    lateinit var car: Car

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        FuelManager.instance.basePath = "http://www.teslausb-281110.ey.r.appspot.com"
    }

    fun httpPostJson(view: View) {
        println("Creating car object")
        val editVIN: EditText = findViewById(R.id.editVIN)
        this.car = Car(editVIN.text.toString())
        ClipRequest.postRequest(this.car)
    }

    fun viewClips(view: View) {
        if(car.fetched){
            val thumbnailView: ImageView = findViewById(R.id.thumbnail)
            var b: Bitmap? = null
            /*for(video in car.savedClips){
                b = getThumbnail(video)
            }*/
            thumbnailView.setImageBitmap(getThumbnail(car.savedClips[0]))
        }
    }

    private fun addVideoView(url: String) {
        val output: TextView = findViewById(R.id.outputText)
        var newText: String = "${output.text}" + "\n$url"
        if (output.text == "") {
            newText = url
        }
        output.text = newText
        //val thumbnailView: ImageView = findViewById(R.id.thumbnail)
        //val b = fillThumbnailView(url)
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

    private fun getThumbnail(videoPath: String): Bitmap {
        println("Displaying thumbnail")
        val mmr = FFmpegMediaMetadataRetriever()
        mmr.setDataSource(videoPath)
        val b = mmr.getFrameAtTime(
            1000000,
            FFmpegMediaMetadataRetriever.OPTION_CLOSEST
        ) // frame at 1 second
        mmr.release()
        return b
    }
}
