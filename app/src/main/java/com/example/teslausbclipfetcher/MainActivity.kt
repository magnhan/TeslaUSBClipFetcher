package com.example.teslausbclipfetcher

import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import wseemann.media.FFmpegMediaMetadataRetriever


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun httpPostJson(view: View) {
        val editVIN: EditText = findViewById(R.id.editVIN)
        val thisRequest: ClipRequest = postRequest(editVIN.text.toString())
        println("request: ${thisRequest.sentryClips}")
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
