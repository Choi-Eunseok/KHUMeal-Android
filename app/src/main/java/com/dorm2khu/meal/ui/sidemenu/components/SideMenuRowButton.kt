package com.dorm2khu.meal.ui.sidemenu.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SideMenuRowButton(
    modifier: Modifier = Modifier,
    title: String,
    icon: @Composable () -> Unit,
    isSelected: Boolean = false,
    onClick: () -> Unit
) {
    val shape = RoundedCornerShape(14.dp)

    Surface(
        color = Color.Transparent,
        contentColor = Color.White,
        shape = shape,
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(shape)
            .clickable { onClick() }
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.size(24.dp), contentAlignment = Alignment.Center) {
                icon()
            }
            Spacer(Modifier.width(12.dp))
            Text(
                text = title,
                style = TextStyle(fontSize = 16.sp),
                color = Color.White
            )
        }
    }

    Spacer(Modifier.height(10.dp))
}