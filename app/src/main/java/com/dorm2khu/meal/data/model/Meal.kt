package com.dorm2khu.meal.data.model

import com.dorm2khu.meal.BuildConfig

data class DailyMealInfo(
    val restaurantName: String,
    val date: String,
    val menuInfos: List<MenuInfo>
)

data class MenuInfo(
    val uuid: String,
    val cornerName: String,
    val items: List<MenuItem>,
    val imageUrl: String?
) {
    fun fullImageUrl(): String? =
        imageUrl?.let { BuildConfig.BASE_URL.trimEnd('/') + it }
}

data class MenuItem(
    val uuid: String,
    val name: String
)

enum class MealContentMode {
    Text,
    Image
}