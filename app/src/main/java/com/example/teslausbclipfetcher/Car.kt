package com.example.teslausbclipfetcher

data class Car(val vin: String) {
    lateinit var sentryClips: List<String>
    lateinit var savedClips: List<String>
    var fetched: Boolean = false

    fun setClips(savedClips: List<String>, sentryClips: List<String>){
        this.sentryClips = sentryClips
        this.savedClips = savedClips
        this.fetched = true
        println("Clips fetched for ${this.vin}")
    }

    fun newLoadReady(){
        this.fetched = false;
    }

}