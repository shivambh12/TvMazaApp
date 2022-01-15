package com.example.movieslist.models
import android.os.Parcel
import android.os.Parcelable
import org.json.JSONObject
import java.io.Serializable

data class movie(
    val imageUrl:String?,
    val title:String?,
    val desc:String?,
    val url:String
):Serializable