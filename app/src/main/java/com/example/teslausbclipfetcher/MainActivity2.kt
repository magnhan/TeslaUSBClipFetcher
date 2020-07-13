package com.example.teslausbclipfetcher

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
    }

    companion object{
        fun getIntent(context: Context, clips: List<String>): Intent {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("CLIPS", clips as ArrayList<String>)
            return intent
        }
    }
}