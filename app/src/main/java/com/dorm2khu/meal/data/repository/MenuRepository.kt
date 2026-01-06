package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.model.DailyMealInfo

interface MenuRepository {
    suspend fun getThisWeekMenu(
        restaurantId: Int
    ): List<DailyMealInfo>
}