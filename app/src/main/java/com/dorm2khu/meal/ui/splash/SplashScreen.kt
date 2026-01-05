package com.dorm2khu.meal.ui.splash

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import com.dorm2khu.meal.R
import com.dorm2khu.meal.ui.common.SetStatusBar
import com.dorm2khu.meal.ui.theme.MainRed

@Composable
fun SplashScreen(
    startFadeIn: Boolean
) {
    SetStatusBar(color = MainRed, darkIcons = false)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainRed),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = startFadeIn,
            enter = fadeIn(animationSpec = tween(durationMillis = 300))
        ) {
            Image(
                painter = painterResource(id = R.drawable.launch_icon),
                contentDescription = "LaunchIcon",
                modifier = Modifier.size(300.dp)
            )
        }
    }
}