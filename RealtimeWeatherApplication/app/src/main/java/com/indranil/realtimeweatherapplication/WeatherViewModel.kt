package com.indranil.realtimeweatherapplication

import android.util.Log
import androidx.lifecycle.ViewModel

class WeatherViewModel :ViewModel() {
    fun getData(city : String) {
        Log.i("city Name", city)
    }
}