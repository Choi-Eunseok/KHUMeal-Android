package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.model.Restaurant

interface RestaurantRepository {
    suspend fun getRestaurants(): List<Restaurant>
}