package com.example.testapp.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.databinding.ItemMovieListBinding
import com.example.testapp.models.filmmodels.Film
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation

class MovieListItemViewHolder(
    private val binding: ItemMovieListBinding,
    private val onClick: ((Film) -> Unit)?,
    private val picasso: Picasso
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(film: Film) {

        binding.root.setOnClickListener {
            onClick?.let { click -> click(film) }
        }

        val path = buildImageUrl(film.posterPath)
        println(path)

        binding.title.text = film.title
        binding.descTV.text = film.overview

        picasso.load(path)
            .transform(RoundedCornersTransformation(10, 0))
            .into(binding.image)

    }

    private fun buildImageUrl(path: String) = "https://image.tmdb.org/t/p/w500$path"
}