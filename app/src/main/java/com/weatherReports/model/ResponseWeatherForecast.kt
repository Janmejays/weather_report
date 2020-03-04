package com.weatherReports.model


data class ResponseWeatherForecast(
    val city: City,
    val cnt: String,
    val cod: String,
    val list: ArrayList<X>,
    val message: String
)

data class City(
    val coord: Coord,
    val country: String,
    val id: String,
    val name: String,
    val population: String,
    val sunrise: String,
    val sunset: String,
    val timezone: String
)

data class X(
    val clouds: Clouds,
    val dt: String,
    val dt_txt: String,
    val main: Main,
    val sys: Sys,
    val weather: List<Weather>,
    val wind: Wind
)

