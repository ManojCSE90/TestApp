package com.example.testapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ItemMovieListBinding
import com.example.testapp.models.filmmodels.Film
import com.example.testapp.viewholders.MovieListItemViewHolder
import com.squareup.picasso.Picasso
import javax.inject.Inject

class MovieListAdapter @Inject constructor(private val picasso: Picasso) :
    RecyclerView.Adapter<MovieListItemViewHolder>() {

    private val movieList = mutableListOf<Film>()
    private var onclik: ((Film) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListItemViewHolder {

        val binding =
            ItemMovieListBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MovieListItemViewHolder(binding, onclik, picasso)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun updateData(list: List<Film>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MovieListItemViewHolder, position: Int) {

        holder.bind(movieList[position])
    }

    fun setOnClick(click: (Film) -> Unit) {
        onclik = click
    }
}