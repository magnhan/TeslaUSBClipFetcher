package com.example.teslausbclipfetcher

import com.google.gson.annotations.SerializedName

data class Clips (
    @SerializedName("SentryClips") val sentryClips: List<String>,
    @SerializedName("SavedClips") val savedClips: List<String>
)
/*data class Result (
    val SentryClips: List<String>,
    val SavedClips: List<String>
)*/
    /*@SerializedName("car_name") val carName: String?,
    @SerializedName("SavedClips") val savedClips: Array<String>?,
    @SerializedName("SentryClips") val sentryClips: Array<String>?
){
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Clips

        if (savedClips != null) {
            if (other.savedClips == null) return false
            if (!savedClips.contentEquals(other.savedClips)) return false
        } else if (other.savedClips != null) return false
        if (sentryClips != null) {
            if (other.sentryClips == null) return false
            if (!sentryClips.contentEquals(other.sentryClips)) return false
        } else if (other.sentryClips != null) return false

        return true
    }

    override fun hashCode(): Int {
        var result = savedClips?.contentHashCode() ?: 0
        result = 31 * result + (sentryClips?.contentHashCode() ?: 0)
        return result
    }
}*/
