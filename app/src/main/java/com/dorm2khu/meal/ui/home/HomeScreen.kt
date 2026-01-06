package com.dorm2khu.meal.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.dorm2khu.meal.data.model.MealContentMode
import com.dorm2khu.meal.ui.common.SetStatusBar
import com.dorm2khu.meal.ui.common.TopBar
import com.dorm2khu.meal.ui.home.components.WeeklyMealPager
import com.dorm2khu.meal.ui.theme.MainRed

@Composable
fun HomeScreen(
    state: HomeUiState,
    mode: MealContentMode,
    onMenuTapped: () -> Unit,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit,
    onRequestScrollToToday: () -> Int
) {
    SetStatusBar(
        color = MainRed,
        darkIcons = false
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainRed)
            .systemBarsPadding()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TopBar (
                title = state.restaurantName,
                frontColor = Color.White,
                onMenuTapped = onMenuTapped
            )

            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            } else {
                WeeklyMealPager(
                    modifier = Modifier,
                    mode = mode,
                    weeklyMeals = state.weeklyMeals,
                    highlightedUuids = state.highlightedUuids,
                    onHighlightChanged = onHighlightChanged,
                    scrollToIndex = onRequestScrollToToday()
                )
            }
        }
    }
}