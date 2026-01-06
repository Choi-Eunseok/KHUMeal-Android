package com.dorm2khu.meal.data.datasource.remote

import com.dorm2khu.meal.data.api.UserApiService
import com.dorm2khu.meal.data.model.dto.UserHighlightRequestDto
import com.dorm2khu.meal.data.model.dto.UserHighlightResponseDto
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: UserApiService
) {
    suspend fun syncUser(body: UserSyncRequestDto) {
        api.syncUser(body)
    }

    suspend fun fetchUserHighlights(userId: String, menuUuidsCsv: String): UserHighlightResponseDto {
        return api.fetchUserHighlights(
            userId = userId,
            menuUuids = menuUuidsCsv
        )
    }

    suspend fun updateHighlight(body: UserHighlightRequestDto) {
        api.updateHighlight(body)
    }
}