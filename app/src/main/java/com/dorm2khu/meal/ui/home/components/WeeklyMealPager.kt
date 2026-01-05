package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.ui.home.model.WeeklyMealUiModel

@Composable
fun WeeklyMealPager(
    modifier: Modifier = Modifier,
    weeklyMeals: List<WeeklyMealUiModel>,
    highlightedUuids: Set<String>,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit,
    scrollToIndex: Int
) {
    LazyRow(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 24.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(weeklyMeals) { _, meal ->
            MealDayCard(
                modifier = Modifier,
                meal = meal,
                highlightedUuids = highlightedUuids,
                onHighlightChanged = onHighlightChanged
            )
        }
    }
}