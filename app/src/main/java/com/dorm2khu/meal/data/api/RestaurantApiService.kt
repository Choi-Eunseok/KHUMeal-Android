package com.dorm2khu.meal.data.api

import com.dorm2khu.meal.data.model.dto.RestaurantDto
import retrofit2.http.GET

interface RestaurantApiService {

    @GET("/api/restaurant")
    suspend fun getRestaurants(): List<RestaurantDto>

}