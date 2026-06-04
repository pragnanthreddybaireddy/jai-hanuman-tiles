package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Premium Editorial Typography using built-in system fonts
val EditorialSerif = FontFamily.Serif
val EditorialSans = FontFamily.Default

val Typography = Typography(
  displayLarge = TextStyle(
    fontFamily = EditorialSerif,
    fontWeight = FontWeight.Light,
    fontSize = 38.sp,
    lineHeight = 44.sp,
    letterSpacing = (-1).sp
  ),
  displayMedium = TextStyle(
    fontFamily = EditorialSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 32.sp,
    lineHeight = 38.sp,
    letterSpacing = (-0.5).sp
  ),
  headlineLarge = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 24.sp,
    lineHeight = 32.sp,
    letterSpacing = 0.sp
  ),
  headlineMedium = TextStyle(
    fontFamily = EditorialSerif,
    fontWeight = FontWeight.Normal,
    fontStyle = FontStyle.Italic,
    fontSize = 20.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.15.sp
  ),
  titleLarge = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.Bold,
    fontSize = 20.sp,
    lineHeight = 26.sp,
    letterSpacing = 0.1.sp
  ),
  titleMedium = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.SemiBold,
    fontSize = 16.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.1.sp
  ),
  bodyLarge = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.Normal,
    fontSize = 15.sp,
    lineHeight = 22.sp,
    letterSpacing = 0.25.sp
  ),
  bodyMedium = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.Normal,
    fontSize = 13.sp,
    lineHeight = 18.sp,
    letterSpacing = 0.25.sp
  ),
  labelLarge = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.Bold,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 1.5.sp
  ),
  labelSmall = TextStyle(
    fontFamily = EditorialSans,
    fontWeight = FontWeight.Bold,
    fontSize = 10.sp,
    lineHeight = 12.sp,
    letterSpacing = 2.sp
  )
)

