package com.dorm2khu.meal.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.compose.rememberAsyncImagePainter

@Composable
fun MealImageView(
    imageUrl: String?,
    modifier: Modifier = Modifier
) {
    if (imageUrl.isNullOrBlank()) {
        Spacer(modifier = modifier.height(1.dp))
        return
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
    ) {
        val painter = rememberAsyncImagePainter(model = imageUrl)
        val size = painter.intrinsicSize
        val ratio =
            if (size.width > 0f && size.height > 0f) size.width / size.height
            else null

        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .then(
                    if (ratio != null) Modifier.aspectRatio(ratio) else Modifier
                ),
            contentScale = ContentScale.Crop
        ) {
            SubcomposeAsyncImageContent()
        }

        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(0.dp)
        ) {
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(Color.White)
            )
            Box(
                Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .align(androidx.compose.ui.Alignment.CenterEnd)
                    .background(Color.White)
            )
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.White)
            )
        }
    }
}