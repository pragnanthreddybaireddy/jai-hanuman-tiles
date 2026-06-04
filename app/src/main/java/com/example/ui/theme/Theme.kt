package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyApplicationTheme(
  content: @Composable () -> Unit,
) {
  // Let's dynamically construct our ColorScheme based on our dynamic state colors
  val dynamicScheme = darkColorScheme(
    primary = GoldPrimary,
    onPrimary = CharcoalDark,
    primaryContainer = GoldDark,
    onPrimaryContainer = TextLight,
    secondary = GoldAccent,
    onSecondary = CharcoalDark,
    background = CharcoalDark,
    onBackground = TextLight,
    surface = CharcoalCard,
    onSurface = TextLight,
    surfaceVariant = CharcoalLight,
    onSurfaceVariant = TextLight,
    outline = GoldPrimary
  )

  MaterialTheme(
    colorScheme = dynamicScheme,
    typography = Typography,
    content = content
  )
}
