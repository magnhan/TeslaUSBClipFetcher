package com.example.teslausbclipfetcher

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Contacts
import android.util.Log.i
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wseemann.media.FFmpegMediaMetadataRetriever
import kotlin.concurrent.thread

class ThumbnailGroupActivity : AppCompatActivity() {

    private var clipsGotten: ArrayList<String> = ArrayList<String>()
    private var clipSize: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_videogroup)

        val bun: Bundle? = intent.extras

        if(bun != null) {
            @Suppress("UNCHECKED_CAST")
            this.clipsGotten = bun.get("CLIPS") as ArrayList<String>
            this.clipSize = this.clipsGotten.size/4
        }
        /*for(link in clipsGotten){
            linkText.append("$link\n")
        }*/
        loadThumbnails()
    }

    private fun loadThumbnails(){
        println("LOADING THUMBNAILS")
        val linearLayout: LinearLayout = findViewById(R.id.linearLayout)
        val thumbnailLayouts: ArrayList<ImageView> = ArrayList<ImageView>()
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 10, 10, 0)
        for(i in 0 until clipSize) {
            val thumbnailView = ImageView(this)
            thumbnailView.layoutParams = params
            thumbnailView.id = i
            thumbnailView.setImageBitmap(getThumbnail(clipsGotten[i*4]))
            thumbnailLayouts.add(thumbnailView)
        }
        for(view in thumbnailLayouts){
            linearLayout.addView(view)
        }

    }

    companion object{
        fun getIntent(context: Context, clips: List<String>): Intent {
            val intent = Intent(context, ThumbnailGroupActivity::class.java)
            intent.putExtra("CLIPS", clips as ArrayList<String>)
            return intent
        }
    }

    private fun getThumbnail(videoPath: String): Bitmap {
        println("Displaying thumbnail for $videoPath")
        val mmr = FFmpegMediaMetadataRetriever()
        mmr.setDataSource(videoPath)
        val b = mmr.getFrameAtTime(
            100000,
            FFmpegMediaMetadataRetriever.OPTION_CLOSEST
        )
        mmr.release()
        return b
    }
}

