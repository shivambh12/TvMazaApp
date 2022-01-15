package com.example.movieslist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieslist.R
import com.example.movieslist.models.movie

class MoviesAdapter(private val listner:Newsitemclicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<movie> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.itemmovie,parent,false)
        val viewholder=NewsViewHolder(view)
        view.setOnClickListener{
            listner.onitemclicked(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val curentirem=items[position]
        holder.title.text=curentirem.title
        holder.Descrption.text=curentirem.desc
        Glide.with(holder.itemView.context).load(curentirem.imageUrl).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return items.size
    }
    fun updatemovies(updateitems :ArrayList<movie>)
    {
        items.clear()
        items.addAll(updateitems)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title: TextView=itemView.findViewById(R.id.title)
    val thumbnail: ImageView=itemView.findViewById(R.id.image)
    val Descrption:TextView=itemView.findViewById(R.id.desc)
}
interface Newsitemclicked
{
    fun onitemclicked(item: movie)
}