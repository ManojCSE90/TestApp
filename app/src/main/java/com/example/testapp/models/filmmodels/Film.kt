package com.example.testapp.models.filmmodels

import com.google.gson.annotations.SerializedName

data class Film(val id: Long,
                val title: String,
                val overview: String,
                @SerializedName("poster_path") val posterPath: String,
                @SerializedName("backdrop_path") val backdropPath: String?,
                @SerializedName("vote_average") val voteAverage: String?)
