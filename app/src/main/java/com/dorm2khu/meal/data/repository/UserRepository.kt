package com.dorm2khu.meal.data.repository

interface UserRepository {
    suspend fun fetchUserHighlights(userId: String, uuids: List<String>): Set<String>
    suspend fun updateHighlight(userId: String, menuItemUuid: String, isHighlighted: Boolean)
}