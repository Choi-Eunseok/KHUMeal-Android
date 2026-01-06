package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorm2khu.meal.data.model.DailyMealInfo
import com.dorm2khu.meal.data.model.MealContentMode
import com.dorm2khu.meal.util.toKoreanDateLabels

@Composable
fun MealDayCard(
    modifier: Modifier = Modifier,
    meal: DailyMealInfo,
    mode: MealContentMode,
    highlightedUuids: Set<String>,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit
) {
    val date = meal.date.toKoreanDateLabels()

    val topShape = androidx.compose.foundation.shape.RoundedCornerShape(
        topStart = 30.dp, topEnd = 30.dp, bottomStart = 0.dp, bottomEnd = 0.dp
    )
    val bottomShape = androidx.compose.foundation.shape.RoundedCornerShape(
        topStart = 0.dp, topEnd = 0.dp, bottomStart = 30.dp, bottomEnd = 30.dp
    )

    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(topShape)
                .background(Color.White)
                .padding(top = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = date.dayLabel,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = date.dateLabel,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black
            )

            Spacer(Modifier.height(16.dp))

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                thickness = 1.dp,
                color = Color(0xFFE5E5EA)
            )
        }

        val gradient = Brush.verticalGradient(
            colors = listOf(
                Color.White,
                Color.White.copy(alpha = 0.5f)
            )
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(bottomShape)
                .background(gradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 20.dp, bottom = 30.dp)
                    .padding(horizontal = 20.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                meal.menuInfos.forEach { menuInfo ->
                    MealCategoryCard(
                        menuInfo = menuInfo,
                        mode = mode,
                        highlightedUuids = highlightedUuids,
                        onHighlightChanged = onHighlightChanged
                    )
                }
            }
        }
    }
}
