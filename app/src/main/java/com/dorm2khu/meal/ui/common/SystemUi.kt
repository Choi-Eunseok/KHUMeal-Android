package com.dorm2khu.meal.ui.common

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat

/**
 * 상태바 색상 + 아이콘 색상 제어
 *
 * @param color 상태바 배경색
 * @param darkIcons true = 검정 아이콘, false = 흰 아이콘
 */
@Composable
fun SetStatusBar(
    color: Color,
    darkIcons: Boolean
) {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = color.toArgb()
        WindowInsetsControllerCompat(window, view)
            .isAppearanceLightStatusBars = darkIcons
    }
}
