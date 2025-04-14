package com.pmaita.mov.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200   = Color(0xFF03DAC5)
val Teal700   = Color(0xFF018786)
val White     = Color(0xFFFFFFFF)
val Black     = Color(0xFF000000)
val Purple200 = Color(0xFFBB86FC)

private val LightColors = lightColorScheme(
    primary = Purple500,
    onPrimary = White,
    primaryContainer = Purple700,
    onPrimaryContainer = White,
    secondary = Teal200,
    onSecondary = Black,
    secondaryContainer = Teal700,
    onSecondaryContainer = White,
    background = White,
    surface = White,
    onBackground = Black,
    onSurface = Black,
    error = Color(0xFFB00020),
    onError = White
)

private val DarkColors = darkColorScheme(
    primary = Purple200,
    onPrimary = Black,
    primaryContainer = Purple700,
    onPrimaryContainer = White,
    secondary = Teal200,
    onSecondary = Black,
    background = Black,
    surface = Black,
    onBackground = White,
    onSurface = White,
    error = Color(0xFFCF6679),
    onError = Black
)

@Composable
fun MovTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}