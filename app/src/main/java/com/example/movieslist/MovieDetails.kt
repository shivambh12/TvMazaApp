package com.example.movieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.bumptech.glide.Glide
import com.example.movieslist.models.movie
import kotlinx.android.synthetic.main.activity_movie_details.*

class MovieDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        //val actionBar = supportActionBar
        //actionBar.title = "MovieDetails"
        val intent=intent
        val ob=intent.getSerializableExtra("Movies") as movie
        webView.apply {
            webViewClient= WebViewClient()
            loadUrl(ob.url)
        }
    }
}