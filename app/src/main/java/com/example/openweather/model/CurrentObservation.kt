package com.example.openweather.model

data class CurrentObservation(
    val astronomy: Astronomy,
    val atmosphere: Atmosphere,
    val condition: Condition,
    val pubDate: Int,
    val wind: Wind
)