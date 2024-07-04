package com.example.openweather

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.openweather.databinding.ActivityMainBinding
import com.example.openweather.databinding.ItemForecastBinding
import com.example.openweather.utils.JsonReader
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get JSON data
        val jsonString = JsonReader().readJsonFromAssets(this, "capeTownResponse.json")
        val capeTownWeather = JsonReader().parseJsonToModel(jsonString)


        ViewCompat.setOnApplyWindowInsetsListener(binding.mainScrollView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.cityName.text = capeTownWeather.location.city
        binding.countryName.text = capeTownWeather.location.country
        binding.conditionText.text = capeTownWeather.current_observation.condition.text
        binding.temperature.text = "Temp: ${capeTownWeather.current_observation.condition.temperature}°F"
        binding.conditionDescription.text = "Text: ${capeTownWeather.current_observation.condition.text}"
        binding.windChill.text = "Chill: ${capeTownWeather.current_observation.wind.chill}°F"
        binding.windSpeed.text = "Speed: ${capeTownWeather.current_observation.wind.speed} mph"
        binding.humidity.text = "Humidity: ${capeTownWeather.current_observation.atmosphere.humidity}%"
        binding.sunset.text = "Sunset: ${capeTownWeather.current_observation.astronomy.sunset}"

        val forecastsContainer = binding.forecastsContainer
        val inflater = LayoutInflater.from(this)

        capeTownWeather.forecasts.forEach { forecast ->
            val itemBinding = ItemForecastBinding.inflate(inflater, forecastsContainer, false)
            itemBinding.day.text = forecast.day
            itemBinding.high.text = "High: ${forecast.high}°F"
            itemBinding.low.text = "Low: ${forecast.low}°F"
            itemBinding.forecastText.text = forecast.text

            forecastsContainer.addView(itemBinding.root)
        }
    }
}
