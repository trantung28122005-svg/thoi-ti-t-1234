package com.example.weatherwidgetapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class WeatherResponse(
    val current: CurrentWeather
)

data class CurrentWeather(
    val temperature_2m: Double,
    val weather_code: Int?
)

interface WeatherApiService {
    @GET("v1/forecast")
    suspend fun getCurrentWeather(
        @Query("latitude") lat: Double = 21.0285, // Hanoi lat
        @Query("longitude") lon: Double = 105.8542, // Hanoi lon
        @Query("current") current: String = "temperature_2m,weather_code"
    ): WeatherResponse
}

object RetrofitClient {
    private const val BASE_URL = "https://api.open-meteo.com/"

    val instance: WeatherApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
    }
}
