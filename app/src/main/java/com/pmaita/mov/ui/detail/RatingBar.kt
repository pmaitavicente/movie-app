package com.pmaita.mov.ui.detail

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun RatingBar(rating: Float, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        repeat(5) { i ->
            val icon = if (i < rating)
                Icons.Default.Star
            else
                Icons.Default.StarBorder
            Icon(
                icon,
                contentDescription = null,
                tint = Color(0xFFFFC107)
            )
        }
    }
}