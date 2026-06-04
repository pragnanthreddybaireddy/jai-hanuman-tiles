package com.example

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.screens.ContactScreen
import com.example.ui.screens.FounderScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.ProductsScreen
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalLight
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.TextLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.theme.AppTheme
import com.example.ui.theme.applyShowroomTheme
import com.example.ui.theme.currentShowroomTheme

enum class ShowroomTab(val title: String) {
    HOME("Home"),
    CATALOG("Catalog"),
    FOUNDER("Founder"),
    INQUIRE("Inquire")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                MainShowroomContainer()
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainShowroomContainer() {
    val context = LocalContext.current
    var currentTab by remember { mutableStateOf(ShowroomTab.HOME) }
    var showInfoDialog by remember { mutableStateOf(false) }
    var showThemeDialog by remember { mutableStateOf(false) }
    
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(CharcoalDark)
    ) {
        val isWide = maxWidth > 720.dp
        
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding(),
            bottomBar = {
                if (!isWide) {
                    // Editorial Navigation Bar matching the provided HTML guidelines (Mobile Only)
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                brush = Brush.verticalGradient(
                                    listOf(BorderLight, Color.Transparent)
                                ),
                                shape = RoundedCornerShape(12.dp)
                            ),
                        color = CharcoalCard.copy(alpha = 0.95f),
                        tonalElevation = 8.dp
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 14.dp, vertical = 12.dp),
                            horizontalArrangement = Arrangement.SpaceAround,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShowroomTab.values().forEach { tab ->
                                val isSelected = currentTab == tab
                                val icon = when (tab) {
                                    ShowroomTab.HOME -> Icons.Filled.Home
                                    ShowroomTab.CATALOG -> Icons.Filled.GridView
                                    ShowroomTab.FOUNDER -> Icons.Filled.School
                                    ShowroomTab.INQUIRE -> Icons.Filled.Drafts
                                }
                                
                                // Premium gold-accent active pill animation
                                Column(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(24.dp))
                                        .clickable { currentTab = tab }
                                        .padding(vertical = 4.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(20.dp))
                                            .background(if (isSelected) GoldPrimary.copy(alpha = 0.15f) else Color.Transparent)
                                            .padding(horizontal = 20.dp, vertical = 6.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = tab.title,
                                            tint = if (isSelected) GoldPrimary else Color.Gray,
                                            modifier = Modifier.size(22.dp)
                                        )
                                    }
                                    Text(
                                        text = tab.title.uppercase(),
                                        style = MaterialTheme.typography.labelSmall,
                                        fontSize = 8.sp,
                                        letterSpacing = 1.sp,
                                        color = if (isSelected) GoldPrimary else Color.Gray,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                }
                            }
                        }
                    }
                }
            },
            floatingActionButton = {
                // Floating Quick Chat action button (Not shown on Inquire screen or Wide websites to prevent cluttering)
                if (currentTab != ShowroomTab.INQUIRE && !isWide) {
                    FloatingActionButton(
                        onClick = {
                            try {
                                val msg = "Hello Jai Hanuman Tiles & Sanitary! I require premium tiles and sanitary materials. Please help me with these brands and materials for my project."
                                val waIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=919246351432&text=${Uri.encode(msg)}"))
                                context.startActivity(waIntent)
                            } catch (e: Exception) {
                                Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                            }
                        },
                        containerColor = Color(0xFF25D366),
                        contentColor = Color.White,
                        shape = CircleShape,
                        elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Chat,
                            contentDescription = "WhatsApp Quick Chat",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(CharcoalDark)
                    .padding(innerPadding)
            ) {
                
                if (isWide) {
                    // STUNNING LUXURY DESKTOP WEB NAVBAR
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CharcoalCard)
                            .border(1.dp, BorderSuperLight)
                            .padding(horizontal = 40.dp, vertical = 18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Web Logo Brand
                        Column(
                            modifier = Modifier.clickable { currentTab = ShowroomTab.HOME }
                        ) {
                            Text(
                                text = "EST. 2012 • DIGITAL SHOWROOM",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldPrimary,
                                fontSize = 9.sp,
                                letterSpacing = 2.sp
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "JAI HANUMAN",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold,
                                    color = TextLight,
                                    letterSpacing = 1.5.sp
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "TILES & SANITARY",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = GoldAccent,
                                    letterSpacing = 1.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }

                        // Wide Web Navigation Tabs
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(28.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            ShowroomTab.values().forEach { tab ->
                                val isSelected = currentTab == tab
                                Column(
                                    modifier = Modifier
                                        .clickable { currentTab = tab }
                                        .padding(horizontal = 14.dp, vertical = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = when (tab) {
                                            ShowroomTab.HOME -> "HOME GALLERY"
                                            ShowroomTab.CATALOG -> "STONE COLLECTION"
                                            ShowroomTab.FOUNDER -> "THE LEADERSHIP"
                                            ShowroomTab.INQUIRE -> "DIGITAL INQUIRY"
                                        },
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) GoldPrimary else TextLight.copy(alpha = 0.8f),
                                        letterSpacing = 1.sp,
                                        fontSize = 13.sp
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Box(
                                        modifier = Modifier
                                            .width(28.dp)
                                            .height(2.dp)
                                            .background(if (isSelected) GoldPrimary else Color.Transparent)
                                    )
                                }
                            }
                        }

                        // Quick Web Controls & Actions
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // WhatsApp Web Callout Button
                            Button(
                                onClick = {
                                    try {
                                        val msg = "Hello Jai Hanuman Tiles & Sanitary! I require premium tiles and sanitary materials. Please help me with these brands and materials for my project."
                                        val waIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=919246351432&text=${Uri.encode(msg)}"))
                                        context.startActivity(waIntent)
                                    } catch (e: Exception) {
                                        Toast.makeText(context, "Could not open WhatsApp", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                                shape = RoundedCornerShape(8.dp),
                                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                            ) {
                                Icon(
                                    Icons.Filled.Chat,
                                    contentDescription = "WhatsApp",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("WHATSAPP INQUIRY", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color.White)
                            }

                            // Theme Selector
                            IconButton(onClick = { showThemeDialog = true }) {
                                Icon(
                                    imageVector = Icons.Filled.Palette,
                                    contentDescription = "Switch Theme",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            
                            // Info Indicator
                            IconButton(onClick = { showInfoDialog = true }) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Showroom Info",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                } else {
                    // 1. STICKY TOP BRAND BAR (Mobile Only)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CharcoalDark)
                            .padding(horizontal = 20.dp, vertical = 14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "EST. 2012",
                                style = MaterialTheme.typography.labelSmall,
                                color = GoldPrimary,
                                fontSize = 8.sp,
                                letterSpacing = 2.sp
                            )
                            Text(
                                text = "JAI HANUMAN",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = TextLight,
                                letterSpacing = 1.sp
                            )
                        }
                        
                        // Fine, elegant round triggers
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // Palette Theme Trigger
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.05f), CircleShape)
                                    .border(1.dp, BorderSuperLight, CircleShape)
                                    .clickable { showThemeDialog = true },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Palette,
                                    contentDescription = "Switch Theme",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            
                            // Info Trigger
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.White.copy(alpha = 0.05f), CircleShape)
                                    .border(1.dp, BorderSuperLight, CircleShape)
                                    .clickable { showInfoDialog = true },
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Showroom info",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
                
                // Subtle glowing progress line indicating current active tab position
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.White.copy(alpha = 0.1f))
                ) {
                    val progressFraction = when (currentTab) {
                        ShowroomTab.HOME -> 0.25f
                        ShowroomTab.CATALOG -> 0.5f
                        ShowroomTab.FOUNDER -> 0.75f
                        ShowroomTab.INQUIRE -> 1.0f
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(progressFraction)
                            .fillMaxHeight()
                            .background(
                                Brush.horizontalGradient(
                                    listOf(GoldPrimary, GoldAccent)
                                )
                            )
                    )
                }
                
                // 2. MAIN ACTIVE TAB CONTAINER WITH CROSSFADE TRANSITIONS (CENTERED FOR WEBSITES)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .then(
                            if (isWide) Modifier
                                .widthIn(max = 1100.dp)
                                .align(Alignment.CenterHorizontally)
                            else Modifier
                        )
                ) {
                    AnimatedContent(
                        targetState = currentTab,
                        transitionSpec = {
                            val enterSpec: FiniteAnimationSpec<Float> = tween(220)
                            val slideEnter = slideInHorizontally(
                                initialOffsetX = { if (targetState.ordinal > initialState.ordinal) 300 else -300 },
                                animationSpec = tween(220)
                            )
                            val slideExit = slideOutHorizontally(
                                targetOffsetX = { if (targetState.ordinal > initialState.ordinal) -300 else 300 },
                                animationSpec = tween(220)
                            )
                            (fadeIn(enterSpec) + slideEnter) togetherWith (fadeOut(enterSpec) + slideExit)
                        },
                        label = "tabContent"
                    ) { targetTab ->
                        when (targetTab) {
                            ShowroomTab.HOME -> {
                                HomeScreen(
                                    onExploreProductsClick = { currentTab = ShowroomTab.CATALOG },
                                    onContactInquiryClick = {
                                        try {
                                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919246351432"))
                                            context.startActivity(intent)
                                        } catch (e: Exception) {
                                            Toast.makeText(context, "Could not open dialer", Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                )
                            }
                            ShowroomTab.CATALOG -> {
                                ProductsScreen()
                            }
                            ShowroomTab.FOUNDER -> {
                                FounderScreen()
                            }
                            ShowroomTab.INQUIRE -> {
                                ContactScreen()
                            }
                        }
                    }
                }
            }
        }
    }
    
    // Quick Premium Info showroom Dialog
    if (showInfoDialog) {
        AlertDialog(
            onDismissRequest = { showInfoDialog = false },
            title = {
                Text(
                    text = "JAI HANUMAN",
                    color = GoldPrimary,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Premium Tiles, Sanitary Ware & Ceramic Custom Solutions.",
                        fontWeight = FontWeight.SemiBold,
                        color = TextLight,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "• Flagship Nagole branch opened in 2012.\n• Hayathnagar branch launched in 2018.\n• Core technical leadership by Bujala Praveen (Ceramics Specialist).",
                        color = TextMuted,
                        fontSize = 13.sp,
                        lineHeight = 18.sp
                    )
                }
            },
            confirmButton = {
                TextButton(onClick = { showInfoDialog = false }) {
                    Text("CLOSE", color = GoldPrimary)
                }
            },
            containerColor = CharcoalCard,
            tonalElevation = 6.dp
        )
    }

    // Dynamic Premium Showroom Palette Selector
    if (showThemeDialog) {
        AlertDialog(
            onDismissRequest = { showThemeDialog = false },
            title = {
                Text(
                    text = "SELECT PALETTE",
                    color = GoldPrimary,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    letterSpacing = 1.sp
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Choose an exquisite visual theme to view Jai Hanuman Ceramic styles in your preferred ambiance:",
                        color = TextLight.copy(alpha = 0.8f),
                        fontSize = 12.sp,
                        lineHeight = 16.sp
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    AppTheme.values().forEach { theme ->
                        val isSelected = currentShowroomTheme == theme
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                .background(if (isSelected) CharcoalLight else Color.Transparent)
                                .border(
                                    width = 1.dp,
                                    color = if (isSelected) GoldPrimary else BorderSuperLight,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable {
                                    applyShowroomTheme(theme)
                                }
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Circular Indicator showing theme background and primary accent
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(
                                        when (theme) {
                                            AppTheme.ONYX -> Color(0xFF0F0F0F)
                                            AppTheme.CARRARA -> Color(0xFFF9F9F7)
                                            AppTheme.EMERALD -> Color(0xFF061814)
                                            AppTheme.SAPPHIRE -> Color(0xFF050814)
                                        }
                                    )
                                    .border(1.dp, BorderLight, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(
                                            when (theme) {
                                                AppTheme.ONYX -> Color(0xFFC5A059)
                                                AppTheme.CARRARA -> Color(0xFFB59453)
                                                AppTheme.EMERALD -> Color(0xFFE0C16C)
                                                AppTheme.SAPPHIRE -> Color(0xFF90CAF9)
                                            }
                                        )
                                )
                            }

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = theme.displayName.uppercase(),
                                    fontWeight = FontWeight.Bold,
                                    color = if (isSelected) GoldPrimary else TextLight,
                                    fontSize = 13.sp
                                )
                                Text(
                                    text = theme.description,
                                    color = TextMuted,
                                    fontSize = 10.sp,
                                    lineHeight = 13.sp
                                )
                            }

                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = "Selected",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showThemeDialog = false }) {
                    Text("DONE", color = GoldPrimary, fontWeight = FontWeight.Bold)
                }
            },
            containerColor = CharcoalCard,
            tonalElevation = 6.dp
        )
    }
}
