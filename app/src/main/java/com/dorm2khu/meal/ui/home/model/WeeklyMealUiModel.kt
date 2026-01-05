package com.dorm2khu.meal.ui.home.model

data class WeeklyMealUiModel(
    val dateLabel: String,
    val menuInfos: List<MenuUiModel>
)

data class MenuUiModel(
    val uuid: String,
    val name: String
)