package com.example.testapp.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.models.NetworkResult
import com.example.testapp.models.filmmodels.Film
import com.example.testapp.viewmodel.TestViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val testViewModel by viewModels<TestViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        registerObserver()

        testViewModel.getTopRatedMovies()

    }

    private fun registerObserver() {

        testViewModel.topRatedMovies.observe(this) {
            updateTopRatedMovies(it)
        }
    }

    private fun updateTopRatedMovies(networkResult: NetworkResult<List<Film>>) {


        when (networkResult) {

            is NetworkResult.Failure -> {
                showOrHideProgress(false)

                println("registerObserver Failure: ${networkResult.errorMsg}")
                Toast.makeText(this, "Api Failed", Toast.LENGTH_SHORT).show()
            }

            is NetworkResult.Loading -> {
                Toast.makeText(this, "Show loader", Toast.LENGTH_SHORT).show()
                showOrHideProgress(true)

                println("registerObserver Loading: $networkResult")

            }

            is NetworkResult.Success -> {
                showOrHideProgress(false)

                Toast.makeText(this, "Api success", Toast.LENGTH_SHORT).show()

                println("registerObserver Success: ${networkResult.data}")

            }
        }

    }

    private fun showOrHideProgress(show: Boolean) {

        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

}
