package com.dorm2khu.meal.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.ui.common.SetStatusBar
import com.dorm2khu.meal.ui.common.TopBar
import com.dorm2khu.meal.ui.home.components.WeeklyMealPager
import com.dorm2khu.meal.ui.theme.MainRed

/**
 * iOS HomeViewController의 View(UI)에 대응되는 Composable
 */
@Composable
fun HomeScreen(
    state: HomeUiState,
    onMenuTapped: () -> Unit,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit,
    onRequestScrollToToday: () -> Int
) {
    // 상태바 (iOS: view.backgroundColor = .mainRed)
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
            // 🔴 TopBar (iOS TopBarView)
            TopBar (
                title = state.restaurantName,
                frontColor = Color.White,
                onMenuTapped = onMenuTapped
            )

            // 🔄 로딩 상태
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
                // 📆 주간 카드 영역 (iOS pagingScrollView)
                WeeklyMealPager(
                    modifier = Modifier,
                    weeklyMeals = state.weeklyMeals,
                    highlightedUuids = state.highlightedUuids,
                    onHighlightChanged = onHighlightChanged,
                    scrollToIndex = onRequestScrollToToday()
                )
            }
        }
    }
}