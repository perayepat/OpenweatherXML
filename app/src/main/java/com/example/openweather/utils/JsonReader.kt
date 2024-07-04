package com.example.openweather.utils

import android.content.Context
import com.example.openweather.model.Forecast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class JsonReader {
    fun readJsonFromAssets(context: Context, fileName: String): String {
        return context.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    fun parseJsonToModel(jsonString: String): Forecast {
        val gson = Gson()
        return gson.fromJson(jsonString, object : TypeToken<Forecast>() {}.type)
    }
}