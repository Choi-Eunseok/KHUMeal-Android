package com.dorm2khu.meal.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeRoute(
    onMenuButtonTapped: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.uiState
    val mode by viewModel.mealContentMode.collectAsState()

    HomeScreen(
        state = state,
        mode = mode,
        onMenuTapped = onMenuButtonTapped,
        onHighlightChanged = viewModel::syncHighlightStatus,
        onRequestScrollToToday = viewModel::scrollToTodayRequested
    )
}