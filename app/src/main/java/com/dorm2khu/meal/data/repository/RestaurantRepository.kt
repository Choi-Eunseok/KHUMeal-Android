package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.model.mapper.RestaurantMapper
import com.dorm2khu.meal.data.model.Restaurant
import com.dorm2khu.meal.data.remote.datasource.RestaurantRemoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepository @Inject constructor(
    private val remote: RestaurantRemoteDataSource,
    private val mapper: RestaurantMapper
) {
    suspend fun getRestaurants(): List<Restaurant> {
        val restaurants = remote.getRestaurants()
        return mapper.toDomainList(restaurants)
    }
}