package com.example.openweather.model

data class Forecast(
    val current_observation: CurrentObservation,
    val forecasts: List<Forecasts>,
    val location: Location
)