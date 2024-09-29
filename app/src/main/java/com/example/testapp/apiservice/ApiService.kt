package com.example.testapp.apiservice

import com.example.testapp.models.filmmodels.FilmApiResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/3/movie/top_rated")
    suspend fun getTopRatedMovie(): Response<FilmApiResponse>

}