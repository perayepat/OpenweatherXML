package com.example.openweather.model

data class Location(
    val city: String,
    val country: String,
    val lat: Double,
    val long: Double,
    val timezone_id: String,
    val woeid: Int
)