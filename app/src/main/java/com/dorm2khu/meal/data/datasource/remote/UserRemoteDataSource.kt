package com.dorm2khu.meal.data.datasource.remote

import com.dorm2khu.meal.data.api.ApiService
import com.dorm2khu.meal.data.model.dto.UserSyncRequestDto
import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun syncUser(body: UserSyncRequestDto) {
        api.syncUser(body)
    }
}