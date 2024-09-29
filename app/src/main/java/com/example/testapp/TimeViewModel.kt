package com.example.testapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.net.URL

class TimeViewModel : ViewModel() {

    private val _btcPrice = MutableLiveData<String>()
    val btcPrice: LiveData<String> get() = _btcPrice

    fun getBtcPrice() {
        viewModelScope.launch(Dispatchers.IO) {
            val btcPrice = JSONObject(URL("https://api.coincap.io/v2/assets/bitcoin").readText())
                .getJSONObject("data")
                .getString("priceUsd")
            _btcPrice.postValue(btcPrice)
        }
    }
}