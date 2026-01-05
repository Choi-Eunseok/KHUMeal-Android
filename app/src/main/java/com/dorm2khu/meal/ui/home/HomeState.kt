package com.dorm2khu.meal.ui.home

import com.dorm2khu.meal.ui.home.model.WeeklyMealUiModel

data class HomeUiState(
    val isLoading: Boolean = true,
    val restaurantName: String = "",
    val weeklyMeals: List<WeeklyMealUiModel> = emptyList(),
    val highlightedUuids: Set<String> = emptySet()
)