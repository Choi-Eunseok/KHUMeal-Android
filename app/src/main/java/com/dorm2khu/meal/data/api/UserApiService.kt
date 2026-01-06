package com.dorm2khu.meal.data.api

import com.dorm2khu.meal.data.model.dto.UserHighlightRequestDto
import com.dorm2khu.meal.data.model.dto.UserHighlightResponseDto
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface UserApiService {

    @POST("/api/v1/users/sync")
    suspend fun syncUser(
        @Body body: UserSyncRequestDto
    )

    @POST("/api/v1/users/highlight")
    suspend fun updateHighlight(
        @Body body: UserHighlightRequestDto
    )

    @GET("/api/v1/users/{userId}/highlights")
    suspend fun fetchUserHighlights(
        @Path("userId") userId: String,
        @Query("menuUuids") menuUuids: String
    ): UserHighlightResponseDto
}