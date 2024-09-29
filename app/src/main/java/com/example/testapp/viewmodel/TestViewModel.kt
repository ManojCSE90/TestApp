package com.example.testapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testapp.models.NetworkResult
import com.example.testapp.models.filmmodels.Film
import com.example.testapp.repository.TestRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val repository: TestRepository) : ViewModel() {

    val topRatedMovies: LiveData<NetworkResult<List<Film>>> = repository.topRatedMovies

    fun getTopRatedMovies(){

        viewModelScope.launch(Dispatchers.IO) {
           repository.getTopRatedMovies()
        }
    }
}