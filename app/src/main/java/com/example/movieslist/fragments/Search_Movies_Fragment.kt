package com.example.movieslist.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.example.movieslist.MainActivity
import com.example.movieslist.MovieDetails
import com.example.movieslist.MySingleton
import com.example.movieslist.R
import com.example.movieslist.adapters.MoviesAdapter
import com.example.movieslist.adapters.Newsitemclicked
import com.example.movieslist.models.movie
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.android.synthetic.main.fragment_search.*

class Search_Movies_Fragment:Fragment(R.layout.fragment_search), Newsitemclicked {
        lateinit var madapter: MoviesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.title = "Search Movies"
        madapter= MoviesAdapter(this)
        rvSearchNews.layoutManager= GridLayoutManager(activity,2)
        val url="https://api.tvmaze.com/search/shows?q="
        search_Button.setOnClickListener {
            if(etSearch.text.toString().isNotEmpty())
            {
                activity?.let { searchnews(url+etSearch.text.toString(), it) }
            }
        }
      madapter?.let {

            rvSearchNews.adapter=madapter
        }
    }
    private fun searchnews(url:String,context: Context)
    {
        val jsonObjectRequest =object: JsonArrayRequest(
            Request.Method.GET, url, null,
            Response.Listener { response ->
                val moviesarray=ArrayList<movie>()
                for(i in 0 until response.length())
                {
                    val jsonobject=response.getJSONObject(i).getJSONObject("show")
                    val movies= movie(jsonobject.getJSONObject("image").getString("original"),
                        jsonobject.getString("name"),
                        jsonobject.getString("summary"),
                        jsonobject.getString("url"))
                    moviesarray.add(movies)
                }
                madapter.updatemovies(moviesarray)
            },
            Response.ErrorListener { error ->
                Log.e("error",error.localizedMessage)
            }
        )
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"]="Mozilla/5.0"
                return headers
            }
        }
        MySingleton.getInstance(context as MainActivity).addToRequestQueue(jsonObjectRequest)
    }

    override fun onitemclicked(item: movie) {
        val intent=Intent(activity,MovieDetails::class.java)
        intent.putExtra("Movies",item);
        startActivity(intent)
    }
}