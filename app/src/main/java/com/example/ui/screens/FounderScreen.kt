package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.TimelineEvent
import com.example.ui.components.ElegantSectionHeader
import com.example.ui.components.GlassCard
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.GlassBackground
import com.example.ui.theme.GoldPrimary

@Composable
fun FounderScreen() {
    val scrollState = rememberScrollState()
    
    val timeline = listOf(
        TimelineEvent("2012", "Flagship Opening at Nagole", "Mr. Bujala Praveen Kumar Reddy ventured into premium ceramics retailing with the core flagship branch at Nagole Crossroads, building associations with leading brands like Kajaria and Johnson."),
        TimelineEvent("2015", "Expansion of Builder Network", "Established deep ties with Hyderabad's elite architects and builders. Sourced huge double-charged vitrified floor tiles & custom water closets for large-scale premium developments."),
        TimelineEvent("2018", "Launch of Hayathnagar Store", "To serve the burgeoning urban demands in Eastern Hyderabad, the second showroom was launched in Hayathnagar, creating a massive flagship space showcasing world-class ceramics."),
        TimelineEvent("2021", "Digitalization & Premium Integration", "Pioneered digitized custom consultation mockups, allowing house owners to pre-conceptualize their bathroom suites before final procurement."),
        TimelineEvent("2026", "A Legacy of Trust", "Continuing to lead the market with exceptional technical advice, premium manufacturer warranties, and direct transparent consulting services.")
    )
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        
        // 1. BRAND HERO FOR FOUNDER
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CharcoalDark)
                .padding(horizontal = 24.dp, vertical = 36.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Large Gold Ceramic Avatar Icon
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .background(GoldPrimary.copy(alpha = 0.15f), CircleShape)
                        .border(1.dp, GoldPrimary, CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Filled.WorkspacePremium,
                        contentDescription = "Founder Signature Symbol",
                        tint = GoldPrimary,
                        modifier = Modifier.size(50.dp)
                    )
                }
                
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "BUJALA PRAVEEN KUMAR REDDY",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "Founder & Ceramic Specialist",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                Text(
                    text = "\"Exquisite spaces start with premium foundations. At Jai Hanuman, we don't just supply tiles—we consult on the science, thickness, and slip-resistance that keep your design looking flawless for generations.\"",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontStyle = FontStyle.Italic,
                        lineHeight = 20.sp
                    ),
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    textAlign = TextAlign.Center
                )
            }
        }
        
        // 2. TIMELINE SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Our Story",
                titleNormal = "Historical",
                titleItalic = "Milestones",
                description = "Follow our continuous trajectory of excellence, trust, and premium sanitary craftsmanship since 2012."
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            timeline.forEachIndexed { index, event ->
                TimelineLineItem(
                    event = event,
                    isLast = index == timeline.size - 1
                )
            }
        }
        
        // 3. SECTOR EXPERTISE CARD
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Leadership Pillars",
                titleNormal = "Strategic",
                titleItalic = "Expertise"
            )
            
            Spacer(modifier = Modifier.height(14.dp))
            
            ExpertiseBulletItem(
                title = "True Technical Consulting",
                description = "Our founder has technical expertise regarding clay density ratios, glaze water-absorption scores and thermal stresses."
            )
            Spacer(modifier = Modifier.height(12.dp))
            ExpertiseBulletItem(
                title = "Direct Brand Partnership",
                description = "Direct procurement channels with industry leaders like Kajaria, Johnson, and RAK resulting in absolute transparent premium pricing."
            )
            Spacer(modifier = Modifier.height(12.dp))
            ExpertiseBulletItem(
                title = "Custom Suite Consultation",
                description = "Curated designs for bungalows, high-rises, and corporate towers matching visual trends, space and budgets."
            )
        }
    }
}

@Composable
fun TimelineLineItem(
    event: TimelineEvent,
    isLast: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 100.dp)
    ) {
        // Vertical Timeline Line & Sphere
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.width(44.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clip(CircleShape)
                    .background(Color.Black)
                    .border(2.dp, GoldPrimary, CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(GoldPrimary, CircleShape)
                )
            }
            
            if (!isLast) {
                Box(
                    modifier = Modifier
                        .width(2.dp)
                        .weight(1f)
                        .background(BorderLight)
                )
            }
        }
        
        // Details
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(bottom = 24.dp)
        ) {
            Text(
                text = event.year,
                style = MaterialTheme.typography.titleMedium,
                color = GoldPrimary,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = event.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                lineHeight = 18.sp
            )
        }
    }
}

@Composable
fun ExpertiseBulletItem(
    title: String,
    description: String
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.padding(20.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Box(
                modifier = Modifier.size(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Verified,
                    contentDescription = "Verified Icon",
                    tint = GoldPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.65f),
                    lineHeight = 16.sp
                )
            }
        }
    }
}
