package com.dorm2khu.meal.data.datasource.remote

import com.dorm2khu.meal.data.api.MealApiService
import com.dorm2khu.meal.data.model.dto.DailyMealInfoDto
import javax.inject.Inject

class MenuRemoteDataSource @Inject constructor(
    private val api: MealApiService
) {
    suspend fun getThisWeekMenu(
        restaurantId: Int
    ): List<DailyMealInfoDto> {
        return api.getThisWeekMenu(restaurantId)
    }
}