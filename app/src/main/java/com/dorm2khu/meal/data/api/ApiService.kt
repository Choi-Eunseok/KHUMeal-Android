package com.dorm2khu.meal.data.api

import com.dorm2khu.meal.data.model.dto.DailyMealInfoDto
import com.dorm2khu.meal.data.model.dto.RestaurantDto
import com.dorm2khu.meal.data.model.dto.UserHighlightRequestDto
import com.dorm2khu.meal.data.model.dto.UserHighlightResponseDto
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @GET("/api/v1/restaurants")
    suspend fun getRestaurants(): List<RestaurantDto>

    @GET("/api/v1/menu/week/this/{restaurantId}")
    suspend fun getThisWeekMenu(
        @Path("restaurantId") restaurantId: Int
    ): List<DailyMealInfoDto>

    @POST("/api/v1/users/sync")
    suspend fun syncUser(
        @Body body: UserSyncRequestDto
    )

    @POST("/api/v1/users/highlight")
    suspend fun updateHighlight(
        @Body body: UserHighlightRequestDto
    ): UserHighlightResponseDto

}