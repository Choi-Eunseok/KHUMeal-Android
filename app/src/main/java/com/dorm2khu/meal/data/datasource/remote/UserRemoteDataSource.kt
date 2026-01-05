package com.dorm2khu.meal.data.datasource.remote

import com.dorm2khu.meal.data.api.UserApiService
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: UserApiService
) {
    suspend fun syncUser(body: UserSyncRequestDto) {
        api.syncUser(body)
    }
}