package com.dorm2khu.meal.ui.home

import com.dorm2khu.meal.data.model.DailyMealInfo

data class HomeUiState(
    val isLoading: Boolean = true,
    val restaurantId: Int? = null,
    val restaurantName: String = "",
    val weeklyMeals: List<DailyMealInfo> = emptyList(),
    val highlightedUuids: Set<String> = emptySet()
)