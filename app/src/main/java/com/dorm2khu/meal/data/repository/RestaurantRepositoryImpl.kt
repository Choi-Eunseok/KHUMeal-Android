package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.datasource.remote.RestaurantRemoteDataSource
import com.dorm2khu.meal.data.model.Restaurant
import com.dorm2khu.meal.data.model.mapper.RestaurantMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RestaurantRepositoryImpl @Inject constructor(
    private val remote: RestaurantRemoteDataSource,
    private val mapper: RestaurantMapper
) : RestaurantRepository {

    override suspend fun getRestaurants(): List<Restaurant> {
        return mapper.toDomainList(remote.getRestaurants())
    }
}