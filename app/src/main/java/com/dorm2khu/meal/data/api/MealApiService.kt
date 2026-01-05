package com.dorm2khu.meal.data.api

import com.dorm2khu.meal.data.model.dto.DailyMealInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface MealApiService {

    @GET("/api/menu/week/this/{restaurantId}")
    suspend fun getThisWeekMenu(
        @Path("restaurantId") restaurantId: Int
    ): List<DailyMealInfoDto>

}