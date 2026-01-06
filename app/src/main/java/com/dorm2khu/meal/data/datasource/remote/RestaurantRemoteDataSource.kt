package com.dorm2khu.meal.data.datasource.remote

import com.dorm2khu.meal.data.api.RestaurantApiService
import com.dorm2khu.meal.data.model.dto.RestaurantDto
import javax.inject.Inject

class RestaurantRemoteDataSource @Inject constructor(
    private val api: RestaurantApiService
) {
    suspend fun getRestaurants(): List<RestaurantDto> {
        return api.getRestaurants()
    }
}