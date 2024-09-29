package com.example.testapp.repository

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.testapp.R
import com.example.testapp.apiservice.ApiService
import com.example.testapp.models.NetworkResult
import com.example.testapp.models.filmmodels.Film
import javax.inject.Inject

class TestRepository @Inject constructor(
    private val resources: Resources,
    private val apiService: ApiService
) {

    private val _topRatedMovies = MutableLiveData<NetworkResult<List<Film>>>()
    val topRatedMovies: LiveData<NetworkResult<List<Film>>> = _topRatedMovies

    suspend fun getTopRatedMovies() {

        try {

            //show loader while getting response from server
            _topRatedMovies.postValue(NetworkResult.Loading())

            val response = apiService.getTopRatedMovie()
            if (response.isSuccessful && response.body() != null) {
                _topRatedMovies.postValue(NetworkResult.Success(response.body()?.results))
            } else {
                _topRatedMovies.postValue(
                    NetworkResult.Failure(
                        response?.errorBody()?.string()
                            ?: resources.getString(R.string.something_went_wrong)
                    )
                )
            }
        } catch (e: Exception) {
            _topRatedMovies.postValue(
                NetworkResult.Failure(
                    e.message ?: resources.getString(R.string.something_went_wrong)
                )
            )

        }
    }
}