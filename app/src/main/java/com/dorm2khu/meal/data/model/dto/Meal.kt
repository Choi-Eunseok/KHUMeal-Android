package com.dorm2khu.meal.data.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class DailyMealInfoDto(
    val restaurantName: String,
    val date: String,
    val menuInfos: List<MenuInfoDto>
)

@Serializable
data class MenuInfoDto(
    val menuInfoUuid: String,
    val cornerName: String,
    val imageUrl: String? = null,
    val menuItems: List<MenuItemDto> = emptyList()
)

@Serializable
data class MenuItemDto(
    val menuItemUuid: String,
    val menuItemName: String
)