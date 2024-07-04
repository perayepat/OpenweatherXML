package com.example.openweather.model

data class Forecasts(
    val code: Int,
    val date: Int,
    val day: String,
    val high: Int,
    val low: Int,
    val text: String
)