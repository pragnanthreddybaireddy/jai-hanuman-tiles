package com.example.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

// Premium dynamic showroom color vars (using Compose state with safe defaults)
var GoldPrimary by mutableStateOf(Color(0xFFC5A059))
var GoldAccent by mutableStateOf(Color(0xFFEADBCE))
var GoldDark by mutableStateOf(Color(0xFF9E845A))

var CharcoalDark by mutableStateOf(Color(0xFF0F0F0F))
var CharcoalCard by mutableStateOf(Color(0xFF161618))
var CharcoalLight by mutableStateOf(Color(0xFF1F2024))

var BorderLight by mutableStateOf(Color(0x1AFFFFFF))
var BorderSuperLight by mutableStateOf(Color(0x0DFFFFFF))
var GlassBackground by mutableStateOf(Color(0x10FFFFFF))

// Static Ceramic / Light Vibe reference values
val CeramicWhite = Color(0xFFFAF9F6) // Soft Alabaster marble
val CeramicCard = Color(0xFFFFFFFF)  // Pure tile white
val CeramicMuted = Color(0xFFECECEF) // Soft gray borders

// Status/Accent Colors
var TextLight by mutableStateOf(Color(0xFFFFFFFF))
var TextMuted by mutableStateOf(Color(0xFFA0A0A5))

var TextDark by mutableStateOf(Color(0xFF1E2022))
var OliveTertiary by mutableStateOf(Color(0xFF6B725C))

// Beautiful Theme Enum Configuration
enum class AppTheme(val displayName: String, val description: String) {
    ONYX("Onyx Empire", "Massive dark obsidian slabs with warm editorial gold accents."),
    CARRARA("Carrara Marble", "Pristine white marble tiles, ambient ivory, and warm brass details."),
    EMERALD("Royal Malachite", "Deep prestigious jade greens styled with rich antique gold lines."),
    SAPPHIRE("Sapphire Palace", "Deep galactic sapphire blue paired with icy platinum curves.")
}

var currentShowroomTheme by mutableStateOf(AppTheme.ONYX)

fun applyShowroomTheme(theme: AppTheme) {
    currentShowroomTheme = theme
    when (theme) {
        AppTheme.ONYX -> {
            GoldPrimary = Color(0xFFC5A059) // Editorial Gold
            GoldAccent = Color(0xFFEADBCE)  // Warm Champagne
            GoldDark = Color(0xFF9E845A)    // Antique Gold
            CharcoalDark = Color(0xFF0F0F0F) // Obsidian Black
            CharcoalCard = Color(0xFF161618) // Elements Black
            CharcoalLight = Color(0xFF1F2024)// Accent highlight
            BorderLight = Color(0x1AFFFFFF)  // Crisp borders
            BorderSuperLight = Color(0x0DFFFFFF) // Soft line
            GlassBackground = Color(0x10FFFFFF)
            TextLight = Color(0xFFFFFFFF)
            TextMuted = Color(0xFFA0A0A5)
            TextDark = Color(0xFF1E2022)
        }
        AppTheme.CARRARA -> {
            GoldPrimary = Color(0xFFB59453) // Brass Gold
            GoldAccent = Color(0xFF8D7344)  // Warm Bronze
            GoldDark = Color(0xFF826330)    // Brushed Gold
            CharcoalDark = Color(0xFFF9F9F7) // Alabaster White Background
            CharcoalCard = Color(0xFFFFFFFF) // Pure Marble Tile Card
            CharcoalLight = Color(0xFFEFEFEE)// Light shadow-edge
            BorderLight = Color(0x1F000000)  // Transparent dark border
            BorderSuperLight = Color(0x0F000000) // Super soft black border
            GlassBackground = Color(0x08000000)  // Mild dark glass overlay
            TextLight = Color(0xFF1F1D1A)    // Dark warm text
            TextMuted = Color(0xFF6B665F)    // Warm Muted Gray
            TextDark = Color(0xFFFFFFFF)
        }
        AppTheme.EMERALD -> {
            GoldPrimary = Color(0xFFE0C16C) // Bright Gold Accent
            GoldAccent = Color(0xFFD3B765)  // Polished Antique Gold
            GoldDark = Color(0xFFFFE082)    // Radiant Brass
            CharcoalDark = Color(0xFF061814) // Rich Jade Forest Dark
            CharcoalCard = Color(0xFF0D2520) // Emerald Card Deep
            CharcoalLight = Color(0xFF14332D)// Emerald Mid
            BorderLight = Color(0x221DE9B6)  // Glowing Mint borders
            BorderSuperLight = Color(0x111DE9B6) // Soft Mint lines
            GlassBackground = Color(0x0E004D40) // Translucent Teal Overlay
            TextLight = Color(0xFFE0F2F1)    // Super Soft Mint White
            TextMuted = Color(0xFF8BA5A0)    // Muted Sage Gray
            TextDark = Color(0xFF1E2022)
        }
        AppTheme.SAPPHIRE -> {
            GoldPrimary = Color(0xFF90CAF9)  // Icy Blue Platinum
            GoldAccent = Color(0xFFBACAE6)   // Soft Platinum Highlight
            GoldDark = Color(0xFF42A5F5)     // Royal Blue Accent
            CharcoalDark = Color(0xFF050814)  // Majestic Midnight Sea
            CharcoalCard = Color(0xFF0E1326)  // Sapphire Deep Space Card
            CharcoalLight = Color(0xFF161F3F) // Oceanic Lagoon
            BorderLight = Color(0x244285F4)   // Electric Sapphire border
            BorderSuperLight = Color(0x0E4285F4) // Soft Blue line
            GlassBackground = Color(0x121A2350)  // Cool Navy glass overlay
            TextLight = Color(0xFFECEFF1)     // Soft Crystal White
            TextMuted = Color(0xFF8D9BB0)     // Soft Midnight Slate
            TextDark = Color(0xFF1E2022)
        }
    }
}
