package com.dorm2khu.meal.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserHighlightRequestDto(
    val userId: String,
    val menuItemUuid: String,
    val isHighlighted: Boolean
)

@Serializable
data class UserHighlightResponseDto(
    val highlightedUuids: List<String>
)