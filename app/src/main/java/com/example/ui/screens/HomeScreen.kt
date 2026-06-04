package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.*
import com.example.ui.components.ElegantSectionHeader
import com.example.ui.components.GlassCard
import com.example.ui.components.LuxuryButton
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.CharcoalLight
import com.example.ui.theme.GoldPrimary
import com.example.ui.theme.GoldAccent
import com.example.ui.theme.TextLight
import com.example.ui.theme.TextMuted
import com.example.ui.theme.GlassBackground
import com.example.ui.theme.AppTheme
import com.example.ui.theme.applyShowroomTheme
import com.example.ui.theme.currentShowroomTheme

@Composable
fun HomeScreen(
    onExploreProductsClick: () -> Unit,
    onContactInquiryClick: () -> Unit
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    
    // Animated states for counter
    var startCounterAnim by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        startCounterAnim = true
    }
    
    val yearsExp by animateIntAsState(
        targetValue = if (startCounterAnim) 20 else 0,
        animationSpec = taylorCubicBezier(durationMillis = 1800),
        label = "years"
    )
    val branchesCount by animateIntAsState(
        targetValue = if (startCounterAnim) 2 else 0,
        animationSpec = taylorCubicBezier(durationMillis = 1500),
        label = "branches"
    )
    val customersCount by animateIntAsState(
        targetValue = if (startCounterAnim) 6 else 0,
        animationSpec = taylorCubicBezier(durationMillis = 2200),
        label = "customers"
    )
    
    val brands = remember {
        listOf(
            BrandInfo("Kajaria", "https://www.kajariaceramics.com", "India's No. 1 Tile Manufacturer specializing in premium vitrified collection."),
            BrandInfo("Johnson", "https://www.johnson-tiles.com", "Legacy brand delivering international quality bathroom solutions."),
            BrandInfo("Somany", "https://www.somanyceramics.com", "Avant-garde ceramic designs and high-gloss dynamic tiles."),
            BrandInfo("Nitco", "https://www.nitco.in", "Luxurious natural looks, designer tiles, and marble finishes."),
            BrandInfo("RAK Ceramics", "https://www.rakceramics.com", "Global powerhouse offering exquisite sanitary collections.")
        )
    }
    
    val branches = remember {
        listOf(
            BranchInfo(
                "nagole",
                "Nagole Branch",
                "2012",
                "The flagship branch located at Nagole has been successfully serving customers since 2012 and remains the foundation of the Jai Hanuman Tiles & Sanitary legacy.",
                listOf("Premium Tiles", "Sanitary Ware", "Bathroom Solutions", "Expert Consultation"),
                "Nagole Cross Roads, Outer Ring Road, Hyderabad, Telangana",
                "Jai Hanuman Tiles & Sanitary Nagole Hyderabad",
                true
            ),
            BranchInfo(
                "hayathnagar",
                "Hayathnagar Branch",
                "2018",
                "Continuing the brand's legacy, the Hayathnagar branch was launched in 2018 to bring premium tile and sanitary solutions closer to customers across Hyderabad.",
                listOf("Complete Tile Range", "Sanitary Products", "Builder Solutions", "Modern Collections"),
                "Vijayawada Highway, Near Word & Deed School, Hayathnagar, Hyderabad, Telangana",
                "Jai Hanuman Tiles & Sanitary Hayathnagar Hyderabad",
                false
            )
        )
    }
    
    val testimonials = remember {
        listOf(
            TestimonialItem("1", "Anirudh Reddy", "Homeowner", 5, "Fabulous collection of premium large-format slabs. Mr. Praveen suggested Carrara glazed vitrified tiles for our hall which looks exceptionally luxurious. Outstanding service!", "Nagole"),
            TestimonialItem("2", "Meenakshi K.", "Architect", 5, "As an designer, I look for precision, sizing variety and trusted brands. Jai Hanuman is our go-to showroom in East Hyderabad. The sanitary ware display is top-tier.", "Secunderabad"),
            TestimonialItem("3", "Satish Kumar", "Builder & Developer", 5, "We have been sourcing vitrified tiles and water closets for our residential projects. Best competitive pricing, fast delivery times, and direct warranty support.", "Hayathnagar"),
            TestimonialItem("4", "Venkatesh Goud", "Villa Owner", 5, "Excellent bathroom layout consultations. Visited their Hayathnagar branch, bought their whole wellness set with premium wash-basins. True guidance!", "Alkapuri")
        )
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        
        // 1. HERO SECTION WITH ABSTRACT luxury backdrop
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 450.dp)
                .drawBehind {
                    drawRect(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                CharcoalLight,
                                CharcoalDark
                            ),
                            center = Offset(size.width * 0.8f, size.height * 0.2f),
                            radius = size.width * 1.2f
                        )
                    )
                    
                    drawLine(
                        color = GoldPrimary,
                        start = Offset(0f, size.height - 1f),
                        end = Offset(size.width, size.height - 1f),
                        strokeWidth = 1f,
                        alpha = 0.3f
                    )
                }
                .padding(horizontal = 24.dp, vertical = 32.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Editorial Tag
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "EST. 2012",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldPrimary,
                        letterSpacing = 2.5.sp
                    )
                    Box(modifier = Modifier.size(4.dp).background(GoldPrimary, CircleShape))
                    Text(
                        text = "HYDERABAD",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
                        letterSpacing = 1.5.sp
                    )
                }
                
                // Big Display Headline
                Column {
                    Text(
                        text = "Building",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                    Text(
                        text = "Beautiful",
                        style = MaterialTheme.typography.displayLarge.copy(
                            fontStyle = FontStyle.Italic,
                            fontWeight = FontWeight.Normal
                        ),
                        color = GoldPrimary
                    )
                    Text(
                        text = "Spaces.",
                        style = MaterialTheme.typography.displayLarge,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
                
                Text(
                    text = "Hyderabad's trusted destination for premium tiles, sanitary ware, and bathroom solutions.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f),
                    modifier = Modifier.heightIn(max = 200.dp)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    LuxuryButton(
                        text = "Explore range",
                        onClick = onExploreProductsClick,
                        modifier = Modifier.weight(1f)
                    )
                    LuxuryButton(
                        text = "Contact Us",
                        onClick = onContactInquiryClick,
                        primary = false,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
        
        // 2. COUNTER SECTION
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(CharcoalDark)
                .border(width = 1.dp, color = BorderSuperLight)
                .padding(vertical = 24.dp, horizontal = 12.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CounterItem(valueText = "${yearsExp}+", label = "Years Legacy")
            CounterItem(valueText = "$branchesCount", label = "Showrooms")
            CounterItem(valueText = "${customersCount} Lakhs+", label = "Happy Clients")
            CounterItem(valueText = "Top", label = "Brands")
        }
        
        // 3. ABOUT COMPANY EDITORIAL
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Our Legacy",
                titleNormal = "About",
                titleItalic = "Jai Hanuman"
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = "Jai Hanuman Tiles & Sanitary is one of Hyderabad's trusted destinations for premium tiles and sanitary solutions. Established in 2012 at Nagole, the company quickly earned a reputation for quality products, expert guidance, and customer satisfaction.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
                lineHeight = 24.sp
            )
            
            Spacer(modifier = Modifier.height(14.dp))
            
            Text(
                text = "With growing customer trust and increasing demand, the second branch was established in Hayathnagar in 2018, continuing the legacy and commitment to excellence.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
                lineHeight = 24.sp
            )
            
            Spacer(modifier = Modifier.height(14.dp))
            
            Text(
                text = "Today, Jai Hanuman Tiles & Sanitary serves homeowners, builders, architects, and interior designers with a wide range of premium products from India's leading brands.",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f),
                lineHeight = 24.sp
            )
        }
        
        // 4. BRANCHES SHOWROOM SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CharcoalCard)
                .padding(horizontal = 24.dp, vertical = 36.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Our Locations",
                titleNormal = "Luxury",
                titleItalic = "Showrooms",
                description = "Visit our physical branches to experience tactile premium ceramic finishes, textures and full sanitary ranges first-hand."
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            branches.forEach { branch ->
                BranchCard(
                    branch = branch,
                    onViewLocation = {
                        openLocationInMaps(context, branch.mapQuery)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
        
        // 5. TESTIMONIAL SLIDER SECTION
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 36.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Patron Reviews",
                titleNormal = "Client",
                titleItalic = "Testimonials",
                description = "Read of the satisfaction experienced by decorators, builders, and elite homeowners."
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            var activeTestimonialIndex by remember { mutableStateOf(0) }
            val activeTestimonial = testimonials[activeTestimonialIndex]
            
            GlassCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .animateContentSize()
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(44.dp)
                                    .background(GoldPrimary.copy(alpha = 0.15f), CircleShape)
                                    .border(1.dp, GoldPrimary.copy(alpha = 0.5f), CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = activeTestimonial.author.first().toString(),
                                    color = GoldPrimary,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 18.sp
                                )
                            }
                            Column {
                                Text(
                                    text = activeTestimonial.author,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = MaterialTheme.colorScheme.onBackground
                                )
                                Text(
                                    text = "${activeTestimonial.role} • ${activeTestimonial.location}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                                )
                            }
                        }
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                            repeat(activeTestimonial.rating) {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Star Rating",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    
                    Text(
                        text = "\"${activeTestimonial.review}\"",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontStyle = FontStyle.Italic,
                            lineHeight = 22.sp
                        ),
                        color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.85f)
                    )
                    
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            testimonials.forEachIndexed { idx, _ ->
                                Box(
                                    modifier = Modifier
                                        .size(if (idx == activeTestimonialIndex) 16.dp else 6.dp, 6.dp)
                                        .clip(CircleShape)
                                        .background(if (idx == activeTestimonialIndex) GoldPrimary else BorderLight)
                                        .clickable { activeTestimonialIndex = idx }
                                )
                            }
                        }
                        
                        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            IconButton(
                                onClick = {
                                    activeTestimonialIndex = if (activeTestimonialIndex > 0) activeTestimonialIndex - 1 else testimonials.size - 1
                                },
                                modifier = Modifier
                                    .size(36.dp)
                                    .border(1.dp, BorderLight, CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowBack,
                                    contentDescription = "Previous testimonial",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            IconButton(
                                onClick = {
                                    activeTestimonialIndex = (activeTestimonialIndex + 1) % testimonials.size
                                },
                                modifier = Modifier
                                    .size(36.dp)
                                    .border(1.dp, BorderLight, CircleShape)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.ArrowForward,
                                    contentDescription = "Next testimonial",
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
        
        // 6. BRANDS LOGO SECTION (Editorial list)
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black.copy(alpha = 0.4f))
                .border(
                    width = 1.dp,
                    brush = Brush.verticalGradient(listOf(BorderSuperLight, Color.Transparent)),
                    shape = RoundedCornerShape(0.dp)
                )
                .padding(vertical = 32.dp)
        ) {
            Text(
                text = "TRUSTED BRANDS WE DEAL WITH",
                style = MaterialTheme.typography.labelSmall,
                color = GoldPrimary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                textAlign = TextAlign.Center,
                letterSpacing = 2.5.sp
            )
            
            Spacer(modifier = Modifier.height(20.dp))
            
            LazyRow(
                contentPadding = PaddingValues(horizontal = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(brands) { brand ->
                    BrandCardView(
                        brand = brand,
                        onClick = {
                            try {
                                val urlIntent = Intent(Intent.ACTION_VIEW, Uri.parse(brand.officialUrl))
                                context.startActivity(urlIntent)
                            } catch (ex: Exception) {
                                Toast.makeText(context, "Could not open website", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun CounterItem(valueText: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = valueText,
            style = MaterialTheme.typography.headlineLarge,
            color = GoldPrimary,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label.uppercase(),
            style = MaterialTheme.typography.labelSmall.copy(fontSize = 9.sp),
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f),
            letterSpacing = 1.sp
        )
    }
}

@Composable
fun BranchCard(
    branch: BranchInfo,
    onViewLocation: () -> Unit
) {
    GlassCard(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column {
                    Text(
                        text = branch.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )
                    Text(
                        text = "Established: ${branch.establishedYear}",
                        style = MaterialTheme.typography.bodyMedium,
                        color = GoldPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                if (branch.isFlagship) {
                    Box(
                        modifier = Modifier
                            .background(GoldPrimary.copy(alpha = 0.15f), RoundedCornerShape(4.dp))
                            .border(1.dp, GoldPrimary, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = "FLAGSHIP",
                            style = MaterialTheme.typography.labelSmall,
                            color = GoldPrimary,
                            fontSize = 8.sp,
                            letterSpacing = 1.sp
                        )
                    }
                }
            }
            
            Text(
                text = branch.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.75f),
                lineHeight = 20.sp
            )
            
            // Features
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                branch.features.chunked(2).forEach { pair ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        pair.forEach { feature ->
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.CheckCircle,
                                    contentDescription = null,
                                    tint = GoldPrimary,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = feature,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
            
            Divider(color = BorderSuperLight, thickness = 1.dp)
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Place,
                    contentDescription = "Address",
                    tint = GoldPrimary,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = branch.address,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                    lineHeight = 16.sp,
                    modifier = Modifier.weight(1f)
                )
            }
            
            Button(
                onClick = onViewLocation,
                colors = ButtonDefaults.buttonColors(
                    containerColor = GoldPrimary,
                    contentColor = Color.Black
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "VIEW LOCATION",
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                    Icon(
                        imageVector = Icons.Filled.Map,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun BrandCardView(
    brand: BrandInfo,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .width(170.dp)
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(CharcoalCard)
            .border(1.dp, BorderSuperLight, RoundedCornerShape(16.dp))
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = brand.name.uppercase(),
                    style = MaterialTheme.typography.titleMedium,
                    color = GoldPrimary,
                    letterSpacing = 1.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = brand.description,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 11.sp,
                    lineHeight = 14.sp,
                    maxLines = 3,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.55f)
                )
            }
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "OFFICIAL SITE",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 8.sp,
                    color = GoldPrimary,
                    letterSpacing = 1.sp
                )
                Icon(
                    imageVector = Icons.Filled.Launch,
                    contentDescription = "Open Website",
                    tint = GoldPrimary,
                    modifier = Modifier.size(10.dp)
                )
            }
        }
    }
}

fun taylorCubicBezier(durationMillis: Int): FiniteAnimationSpec<Int> {
    return tween(
        durationMillis = durationMillis,
        easing = CubicBezierEasing(0.2f, 0.8f, 0.2f, 1.0f)
    )
}

fun openLocationInMaps(context: Context, query: String) {
    try {
        val mapUri = Uri.parse("geo:0,0?q=${Uri.encode(query)}")
        val intent = Intent(Intent.ACTION_VIEW, mapUri)
        intent.setPackage("com.google.android.apps.maps")
        context.startActivity(intent)
    } catch (e: Exception) {
        try {
            val mapWebUri = Uri.parse("https://www.google.com/maps/search/?api=1&query=${Uri.encode(query)}")
            val webIntent = Intent(Intent.ACTION_VIEW, mapWebUri)
            context.startActivity(webIntent)
        } catch (ex: Exception) {
            Toast.makeText(context, "Maps not available", Toast.LENGTH_SHORT).show()
        }
    }
}
