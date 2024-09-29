package com.example.testapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var timeViewModel : TimeViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        intiViewModel()
        registerObserver()
        binding.button.setOnClickListener {
            timeViewModel?.getBtcPrice()
        }

    }

    private fun registerObserver() {

        timeViewModel?.btcPrice?.observe(this) {
            binding.time.text = it
        }
    }

    private fun intiViewModel() {

        timeViewModel = ViewModelProvider(this).get(TimeViewModel::class.java)
    }
}
