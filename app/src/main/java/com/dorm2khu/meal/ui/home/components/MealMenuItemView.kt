package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dorm2khu.meal.ui.theme.HighlightYellow

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MealMenuItemView(
    modifier: Modifier = Modifier,
    text: String,
    isHighlighted: Boolean,
    onToggle: (Boolean) -> Unit
) {
    val haptic = LocalHapticFeedback.current
    var localHighlighted by remember(text) { mutableStateOf(isHighlighted) }

    LaunchedEffect(isHighlighted) { localHighlighted = isHighlighted }

    val bg = if (localHighlighted) HighlightYellow else Color.Transparent

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(bg)
            .combinedClickable(
                onClick = { },
                onDoubleClick = {
                    localHighlighted = !localHighlighted
                    onToggle(localHighlighted)
                    haptic.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                }
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}