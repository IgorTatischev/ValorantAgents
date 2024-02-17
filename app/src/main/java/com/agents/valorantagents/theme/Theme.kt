package com.agents.valorantagents.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Primary,
    background = Primary,
    onPrimary = onPrimary,
    onSurface = onPrimary,
    surface = Surface,
    secondaryContainer = Surface,
    primaryContainer = Secondary,
    onSecondaryContainer = Secondary,
    onSurfaceVariant = Color.White,
)

@Composable
fun ValorantAgentsTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}