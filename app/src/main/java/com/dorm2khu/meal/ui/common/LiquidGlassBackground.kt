package com.dorm2khu.meal.ui.common

import android.os.Build
import android.graphics.RenderEffect as AndroidRenderEffect
import android.graphics.Shader
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeChild

@Composable
fun LiquidGlassBackground(
    hazeState: HazeState,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(0.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .hazeChild(
                state = hazeState,
                shape = shape,
                style = HazeStyle(
                    tint = Color.White.copy(.15f),
                    blurRadius = 24.dp
                )
            )
            .background(Color.White.copy(alpha = 0.08f))
            .border(1.dp, Color.White.copy(alpha = 0.5f), shape),
    )
}