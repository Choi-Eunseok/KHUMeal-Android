package com.dorm2khu.meal.data.repository

import com.dorm2khu.meal.data.datasource.remote.UserRemoteDataSource
import com.dorm2khu.meal.data.model.dto.UserHighlightRequestDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepositoryImpl @Inject constructor(
    private val remote: UserRemoteDataSource
) : UserRepository {

    override suspend fun fetchUserHighlights(userId: String, uuids: List<String>): Set<String> {
        if (uuids.isEmpty()) return emptySet()

        // Swift: uuids.joined(separator: ",")
        val csv = uuids.joinToString(",")

        val resp = remote.fetchUserHighlights(userId, csv)
        return resp.highlightedUuids.toSet()
    }

    override suspend fun updateHighlight(userId: String, menuItemUuid: String, isHighlighted: Boolean) {
        remote.updateHighlight(
            UserHighlightRequestDto(
                userId = userId,
                menuItemUuid = menuItemUuid,
                isHighlighted = isHighlighted
            )
        )
    }
}