package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.ui.home.model.WeeklyMealUiModel

@Composable
fun MealDayCard(
    modifier: Modifier = Modifier,
    meal: WeeklyMealUiModel,
    highlightedUuids: Set<String>,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit
) {
    Column(
        modifier = Modifier
            .width(300.dp)
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = meal.dateLabel,
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(12.dp))

        meal.menuInfos.forEach { menu ->
            val selected = highlightedUuids.contains(menu.uuid)
            Text(
                text = if (selected) "⭐ ${menu.name}" else menu.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp)
                    .clickable {
                        onHighlightChanged(menu.uuid, !selected)
                    }
            )
        }
    }
}