package com.kisan.os.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val KisanEmerald = Color(0xFF10B981)
val KisanEmeraldDark = Color(0xFF059669)
val KisanAmber = Color(0xFFF59E0B)
val KisanLeafGreen = Color(0xFF16A34A)

// Light Theme Colors
val LightBg = Color(0xFFF8FAFC)
val LightSurface = Color(0xFFFFFFFF)
val LightTextPrimary = Color(0xFF0F172A)
val LightTextSecondary = Color(0xFF64748B)

// Dark OLED Theme Colors
val DarkBg = Color(0xFF090D16)
val DarkSurface = Color(0xFF131B2E)
val DarkCard = Color(0xFF1E293B)
val DarkTextPrimary = Color(0xFFF8FAFC)
val DarkTextSecondary = Color(0xFF94A3B8)

val LightColorScheme = lightColorScheme(
    primary = KisanEmerald,
    onPrimary = Color.White,
    secondary = KisanAmber,
    onSecondary = Color.Black,
    background = LightBg,
    surface = LightSurface,
    onBackground = LightTextPrimary,
    onSurface = LightTextPrimary
)

val DarkColorScheme = darkColorScheme(
    primary = KisanEmerald,
    onPrimary = Color.White,
    secondary = KisanAmber,
    onSecondary = Color.Black,
    background = DarkBg,
    surface = DarkSurface,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary
)
