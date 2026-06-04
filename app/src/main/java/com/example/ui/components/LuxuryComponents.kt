package com.example.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.GlassBackground
import com.example.ui.theme.GoldPrimary

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 24.dp,
    borderColor: Color = BorderSuperLight,
    borderWidth: Dp = 1.dp,
    contentAlignment: Alignment = Alignment.TopStart,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(cornerRadius))
            .background(GlassBackground)
            .border(borderWidth, borderColor, RoundedCornerShape(cornerRadius)),
        contentAlignment = contentAlignment,
        content = content
    )
}

@Composable
fun ElegantSectionHeader(
    modifier: Modifier = Modifier,
    categoryLabel: String,
    titleNormal: String,
    titleItalic: String,
    description: String? = null
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(16.dp)
                    .height(2.dp)
                    .background(GoldPrimary)
            )
            Text(
                text = categoryLabel.uppercase(),
                style = MaterialTheme.typography.labelSmall,
                color = GoldPrimary
            )
        }
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "$titleNormal ",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.ExtraLight,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = titleItalic,
                style = MaterialTheme.typography.displayMedium,
                fontStyle = FontStyle.Italic,
                color = GoldPrimary
            )
        }
        
        description?.let {
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = it,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
                lineHeight = 18.sp
            )
        }
    }
}

/**
 * ProceduralTileView draws a highly premium vector texture of ceramic tile elements.
 * Generates visual tiles dynamically:
 * 0 -> Polished Statuario/Carrara Marble (White with refined Gold & Charcoal veining)
 * 1 -> Royal Basalt/Travertine (Deep Charcoal with textured concentric details)
 * 2 -> Terracotta & Wood blend
 * 3 -> Royal Fluted Gold Glaze (Stripe textures)
 * 4 -> Modern terrazzo / mosaic tile layout
 */
@Composable
fun ProceduralTileView(
    modifier: Modifier = Modifier,
    styleIndex: Int,
    label: String? = null
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121214))
            .border(1.dp, BorderSuperLight)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            
            when (styleIndex) {
                0 -> {
                    // Luxurious Carrara Gold Marble
                    drawRect(color = Color(0xFFFAF9F6))
                    
                    // Golden & Charcoal veins
                    val path1 = Path().apply {
                        moveTo(0f, height * 0.15f)
                        quadraticTo(width * 0.35f, height * 0.25f, width * 0.45f, height * 0.5f)
                        lineTo(width * 0.5f, height * 0.52f)
                        quadraticTo(width * 0.75f, height * 0.62f, width, height * 0.85f)
                    }
                    drawPath(
                        path = path1,
                        color = Color(0xFFC5A059),
                        alpha = 0.5f,
                        style = Stroke(width = 4f)
                    )
                    
                    val path2 = Path().apply {
                        moveTo(0f, height * 0.2f)
                        lineTo(width * 0.15f, height * 0.22f)
                        quadraticTo(width * 0.45f, height * 0.35f, width * 0.6f, height * 0.1f)
                        lineTo(width * 0.8f, 0f)
                    }
                    drawPath(
                        path = path2,
                        color = Color(0xFF6B6E70),
                        alpha = 0.35f,
                        style = Stroke(width = 2f)
                    )
                    
                    val path3 = Path().apply {
                        moveTo(width * 0.2f, height)
                        quadraticTo(width * 0.45f, height * 0.7f, width * 0.75f, height * 0.72f)
                        lineTo(width, height * 0.4f)
                    }
                    drawPath(
                        path = path3,
                        color = Color(0xFFC5A059),
                        alpha = 0.6f,
                        style = Stroke(width = 3.5f)
                    )
                    
                    // Subtle highlights / polishing reflection
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(Color.White.copy(alpha = 0.3f), Color.Transparent),
                            center = Offset(width * 0.2f, height * 0.2f),
                            radius = width * 0.8f
                        )
                    )
                }
                
                1 -> {
                    // Royal Basalt/Travertine (Deep elegant charcoal texture with layered bands)
                    drawRect(color = Color(0xFF1E2024))
                    
                    // Concentric fine lines to mimic marble or slate grain
                    for (i in 0..12) {
                        val offsetMultiplier = i / 12f
                        drawCircle(
                            color = Color(0xFFC5A059),
                            radius = width * (0.3f + offsetMultiplier * 0.7f),
                            center = Offset(-50f, -50f),
                            alpha = 0.08f,
                            style = Stroke(width = 1.5f + i * 0.3f)
                        )
                    }
                    
                    // Horizontal texture streaks
                    drawRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.15f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.2f)
                            )
                        )
                    )
                }
                
                2 -> {
                    // Terracotta & Wood Blend / Hex Tile look
                    drawRect(color = Color(0xFF915136)) // Reddish clay
                    
                    val cols = 4
                    val rows = 4
                    val cellW = width / cols
                    val cellH = height / rows
                    
                    for (c in 0..cols) {
                        for (r in 0..rows) {
                            val centerX = c * cellW
                            val centerY = r * cellH
                            
                            val diamondPath = Path().apply {
                                moveTo(centerX, centerY - cellH * 0.25f)
                                lineTo(centerX + cellW * 0.25f, centerY)
                                lineTo(centerX, centerY + cellH * 0.25f)
                                lineTo(centerX - cellW * 0.25f, centerY)
                                close()
                            }
                            drawPath(
                                path = diamondPath,
                                color = Color(0xFFC5A059),
                                alpha = 0.12f,
                                style = Stroke(width = 2f)
                            )
                        }
                    }
                }
                
                3 -> {
                    // Royal Fluted Gold Slate Cladding (3D metallic stripe textures)
                    drawRect(
                        brush = Brush.linearGradient(
                            colors = listOf(Color(0xFF2E2C28), Color(0xFF1F1F1E)),
                            start = Offset(0f, 0f),
                            end = Offset(width, 0f)
                        )
                    )
                    
                    val stripeWidth = width / 10f
                    for (i in 0..10) {
                        val leftX = i * stripeWidth
                        drawRect(
                            brush = Brush.linearGradient(
                                colors = listOf(
                                    Color(0xFFC5A059).copy(alpha = 0.2f),
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.4f)
                                ),
                                start = Offset(leftX, 0f),
                                end = Offset(leftX + stripeWidth, 0f)
                            ),
                            topLeft = Offset(leftX, 0f),
                            size = Size(stripeWidth, height)
                        )
                    }
                }
                
                else -> {
                    // Modern Terrazzo & Mosaic
                    drawRect(color = Color(0xFFF3F2EE))
                    
                    val chips = listOf(
                        Offset(width * 0.2f, height * 0.15f) to Color(0xFFC5A059),
                        Offset(width * 0.35f, height * 0.45f) to Color(0xFF1E2024),
                        Offset(width * 0.75f, height * 0.25f) to Color(0xFFC5A059),
                        Offset(width * 0.6f, height * 0.75f) to Color(0xFF7E8085),
                        Offset(width * 0.15f, height * 0.85f) to Color(0xFFC5A059)
                    )
                    
                    chips.forEach { (pos, color) ->
                        val path = Path().apply {
                            moveTo(pos.x, pos.y - 12f)
                            lineTo(pos.x + 14f, pos.y + 8f)
                            lineTo(pos.x - 10f, pos.y + 12f)
                            close()
                        }
                        drawPath(path = path, color = color, alpha = 0.65f)
                    }
                }
            }
        }
        
        label?.let {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
                    .background(Color.Black.copy(alpha = 0.75f), RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            ) {
                Text(
                    text = it.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldPrimary,
                    fontSize = 8.sp,
                    letterSpacing = 1.sp
                )
            }
        }
    }
}

@Composable
fun LuxuryButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    primary: Boolean = true
) {
    val borderModifier = if (!primary) {
        Modifier.border(1.dp, GoldPrimary, RoundedCornerShape(12.dp))
    } else {
        Modifier
    }
    
    val backgroundBrush = if (primary) {
        Brush.horizontalGradient(
            colors = listOf(GoldPrimary, Color(0xFF9E845A))
        )
    } else {
        Brush.linearGradient(
            colors = listOf(Color.Transparent, Color.White.copy(alpha = 0.05f))
        )
    }
    
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(backgroundBrush)
            .then(borderModifier)
            .clickable(onClick = onClick)
            .padding(vertical = 14.dp, horizontal = 24.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            color = if (primary) Color.Black else GoldPrimary,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
    }
}
