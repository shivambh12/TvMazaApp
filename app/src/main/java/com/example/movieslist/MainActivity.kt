package com.example.movieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.movieslist.adapters.MoviesAdapter
import com.example.movieslist.adapters.Newsitemclicked
import com.example.movieslist.models.movie
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MoviesList)
        setContentView(R.layout.activity_main)
        bottomNavigationView.setupWithNavController(newsNavHostFragment.findNavController())

    }

}

