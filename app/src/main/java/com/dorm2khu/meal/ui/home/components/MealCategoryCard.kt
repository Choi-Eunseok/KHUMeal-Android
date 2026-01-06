package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorm2khu.meal.data.model.MealContentMode
import com.dorm2khu.meal.data.model.MenuInfo

@Composable
fun MealCategoryCard(
    menuInfo: MenuInfo,
    mode: MealContentMode,
    highlightedUuids: Set<String>,
    onHighlightChanged: (uuid: String, isSelected: Boolean) -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = Color.White,
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp),
        shadowElevation = 6.dp
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = menuInfo.cornerName,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 20.dp, end = 20.dp),
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .padding(horizontal = 20.dp),
                thickness = 1.dp,
                color = Color.Black
            )

            when (mode) {
                MealContentMode.Text -> {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, start = 24.dp, end = 24.dp, bottom = 24.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        menuInfo.items.forEach { item ->
                            MealMenuItemView(
                                text = item.name,
                                isHighlighted = highlightedUuids.contains(item.uuid),
                                onToggle = { selected ->
                                    onHighlightChanged(item.uuid, selected)
                                }
                            )
                        }
                    }
                }

                MealContentMode.Image -> {
                    MealImageView(
                        imageUrl = menuInfo.imageUrl,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 4.dp, start = 24.dp, end = 24.dp, bottom = 12.dp)
                    )
                }
            }
        }
    }
}