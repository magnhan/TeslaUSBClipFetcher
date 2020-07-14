package com.example.teslausbclipfetcher

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import wseemann.media.FFmpegMediaMetadataRetriever

class VideoGroupActivity : AppCompatActivity() {

    private var clipsGotten: ArrayList<String> = ArrayList<String>()
    private var clipSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videogroup)

        val bun: Bundle? = intent.extras
        val linkText: TextView = findViewById(R.id.linkText)

        if(bun != null) {
            @Suppress("UNCHECKED_CAST")
            this.clipsGotten = bun.get("CLIPS") as ArrayList<String>
            this.clipSize = this.clipsGotten.size/4
        }
        for(link in clipsGotten){
            linkText.append("$link\n")
        }

    }

    companion object{
        fun getIntent(context: Context, clips: List<String>): Intent {
            val intent = Intent(context, VideoGroupActivity::class.java)
            intent.putExtra("CLIPS", clips as ArrayList<String>)
            return intent
        }
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