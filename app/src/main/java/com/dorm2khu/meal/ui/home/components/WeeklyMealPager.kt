package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.data.model.DailyMealInfo
import com.dorm2khu.meal.data.model.MealContentMode

@Composable
fun WeeklyMealPager(
    modifier: Modifier = Modifier,
    mode: MealContentMode,
    weeklyMeals: List<DailyMealInfo>,
    highlightedUuids: Set<String>,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit,
    scrollToIndex: Int
) {
    val pagerState = rememberPagerState(
        initialPage = scrollToIndex,
        pageCount = { weeklyMeals.size }
    )

    LaunchedEffect(scrollToIndex) {
        if (scrollToIndex in weeklyMeals.indices) {
            pagerState.scrollToPage(scrollToIndex)
        }
    }

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 36.dp),
        pageSpacing = 24.dp
    ) { page ->
        MealDayCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 12.dp),
            meal = weeklyMeals[page],
            mode = mode,
            highlightedUuids = highlightedUuids,
            onHighlightChanged = onHighlightChanged
        )
    }
}