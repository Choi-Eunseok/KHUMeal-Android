package com.dorm2khu.meal.ui.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.dorm2khu.meal.R
import com.dorm2khu.meal.ui.common.SetStatusBar
import com.dorm2khu.meal.ui.common.TopBar
import com.dorm2khu.meal.ui.settings.components.SettingItemSwitch
import com.dorm2khu.meal.ui.theme.MainBlue

@Composable
fun SettingsScreen(
    isImageMode: Boolean,
    onBack: () -> Unit,
    onImageModeChange: (Boolean) -> Unit
) {
    SetStatusBar(color = MainBlue, darkIcons = false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBlue)
    ) {
        Box(modifier = Modifier.statusBarsPadding()) {
            TopBar(
                modifier = Modifier
                    .background(MainBlue),
                title = "설정",
                frontColor = Color.White,
                image = ImageVector.vectorResource(R.drawable.rounded_chevron_left_24),
                onMenuTapped = onBack
            )
        }

        Column(
            modifier = Modifier
                .background(Color(0xFFF2F2F7))
                .fillMaxSize()
                .navigationBarsPadding()
        ) {
            Box(
                modifier = Modifier
                    .padding(24.dp)
            ) {
                SettingItemSwitch(
                    modifier = Modifier,
                    title = "이미지로 식단 보기",
                    checked = isImageMode,
                    onCheckedChange = onImageModeChange
                )
            }
        }
    }
}