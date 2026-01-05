package com.dorm2khu.meal.ui.sidemenu

import com.dorm2khu.meal.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.data.model.Restaurant
import com.dorm2khu.meal.ui.common.LiquidGlassBackground
import com.dorm2khu.meal.ui.sidemenu.components.SideMenuRowButton
import dev.chrisbanes.haze.HazeState

@Composable
fun SideMenu(
    modifier: Modifier = Modifier,
    hazeState: HazeState,
    restaurants: List<Restaurant>,
    selectedRestaurantId: Int?,
    settingsTitle: String = "설정",
    onRestaurantSelected: (Restaurant) -> Unit,
    onSettingsSelected: () -> Unit,
    drawerWidth: Dp = 280.dp
) {
    var initialSent by remember { mutableStateOf(false) }
    LaunchedEffect(restaurants) {
        if (!initialSent && restaurants.isNotEmpty()) {
            initialSent = true
        }
    }

    Box(
        modifier = modifier
            .fillMaxHeight()
            .width(drawerWidth)
    ) {
        LiquidGlassBackground(
            modifier = Modifier.matchParentSize(),
            hazeState = hazeState
        )

        Column(
            modifier = Modifier
                .statusBarsPadding()
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp)
                .navigationBarsPadding()
        ) {
            Text(
                text = "학식",
                fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 4.dp)
            )

            Spacer(Modifier.height(30.dp))

            restaurants.forEach { res ->
                SideMenuRowButton(
                    modifier = Modifier,
                    title = res.name,
                    isSelected = selectedRestaurantId == res.id,
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.rounded_restaurant_24),
                            contentDescription = "Restaurant",
                            tint = Color.White
                        )
                    },
                    onClick = { onRestaurantSelected(res) }
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.5.dp)
                    .background(Color.White.copy(alpha = 0.1f))
            )

            Spacer(Modifier.height(12.dp))

            SideMenuRowButton(
                modifier = Modifier,
                title = settingsTitle,
                isSelected = false,
                icon = {
                    Icon(
                        imageVector = Icons.Rounded.Settings,
                        contentDescription = "Settings",
                        tint = Color.White
                    )
                },
                onClick = onSettingsSelected
            )
        }
    }
}