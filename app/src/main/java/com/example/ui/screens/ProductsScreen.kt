package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ProductCategory
import com.example.data.ProductItem
import com.example.ui.components.ElegantSectionHeader
import com.example.ui.components.GlassCard
import com.example.ui.components.ProceduralTileView
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.GoldPrimary

@Composable
fun ProductsScreen() {
    val context = LocalContext.current
    var searchField by remember { mutableStateOf("") }
    
    val categories = remember {
        listOf(
            ProductCategory("all", "All Items", "Full product catalog browser", "Widgets"),
            ProductCategory("floor", "Floor Tiles", "Luxury vitrified marble look tiles for beautiful drawing rooms", "Floor"),
            ProductCategory("wall", "Wall Tiles", "Perfect ceramic glazes for living facades and dining backdrops", "Wallpaper"),
            ProductCategory("vitrified", "Vitrified Tiles", "Double charged and high glaze heavy duty tiles", "Grid3x3"),
            ProductCategory("bathroom", "Bathroom Tiles", "Satin matte slip-resistant custom layout tiles", "InvertColors"),
            ProductCategory("kitchen", "Kitchen Tiles", "Highlighter tile panels & stain resistant models", "Kitchen"),
            ProductCategory("sanitary", "Sanitary Ware", "One-piece washdowns, closets, and designer vanity basins", "Wash"),
            ProductCategory("washbasins", "Wash Basins", "Tabletop basins, pedestal stands & integrated ceramic basins", "Water"),
            ProductCategory("waterclosets", "Water Closets", "Intelligent wall hung closets and siphonic flushing systems", "Shower"),
            ProductCategory("accessories", "Bathroom Accessories", "Premium chrome-plated fittings, towel racks and health faucets", "Build")
        )
    }
    
    var selectedCategoryId by remember { mutableStateOf("all") }
    
    val products = remember {
        listOf(
            ProductItem(
                "JH-VT-101",
                "Statuario Gold Marble Slab",
                "vitrified",
                "Kajaria Premium",
                "800 x 1600 mm",
                "Polished Glazed Vitrified (PGVT)",
                listOf("Sinuous Golden Veins", "9mm Uniform Slab thickness", "Stain Resistant glaze"),
                "A breath of sheer luxury. Mimics premium Italian Statuario marble with thick, expressive golden-brown paths running across an elegant milky canvas.",
                0
            ),
            ProductItem(
                "JH-FL-201",
                "Royal Carrara Classic Tile",
                "floor",
                "Johnson Tiles",
                "600 x 1200 mm",
                "Polished Vitrified",
                listOf("Highly reflective mirror glaze", "Anti-skid tech glaze", "Zero-joint seamless layouts"),
                "Timeless floor aesthetics designed to make spaces feel monumental, open, and airy. Reflects maximum natural light.",
                0
            ),
            ProductItem(
                "JH-WL-301",
                "Geometric Fluted Accent Block",
                "wall",
                "Somany Ceramics",
                "300 x 600 mm",
                "Glazed Glossy & Metallic Trim",
                listOf("3D Fluted tactile grooves", "Metallic gold highlight bands", "Waterproofing ceramic body"),
                "Modern kitchen highlight panels or stylish bathroom vanity backdrops. Designed with linear ribbed depths for three-dimensional visual play.",
                3
            ),
            ProductItem(
                "JH-BT-401",
                "Satin Basalt Charcoal Series",
                "bathroom",
                "Nitco Tiles",
                "600 x 600 mm",
                "Matte Slip-Resistant Surface",
                listOf("R10 anti-skid safety rating", "Acid & chemical proof", "Earthy textured look"),
                "High density ceramic body with soft rustic slate texture designed for safety, ergonomics and ultimate visual stability in wet areas.",
                1
            ),
            ProductItem(
                "JH-KT-501",
                "Alabaster Mosaic Highlighter",
                "kitchen",
                "Kajaria Premium",
                "300 x 450 mm",
                "Super Glossy Glaze",
                listOf("Scratch proof cooktop backing", "Easy-to-clean stain barrier", "Modular grid layout"),
                "Bright decorative wall highlighter perfect for chimney backdrops or kitchen sink splash areas. Easy oil-repelling glaze makes wiping effortless.",
                4
            ),
            ProductItem(
                "JH-SW-601",
                "Wall Hung Rimless Closet",
                "waterclosets",
                "RAK Ceramics",
                "540 x 360 x 340 mm",
                "Anti-bacterial Glaze Flush",
                listOf("Rimless hygiene design", "Soft close UF seat cover", "Siphonic 3/4.5L dual flush"),
                "European modular sanitary luxury. Wall hanging saves bathroom space while the premium rimless bowl ensures 360-degree cleaning coverage without residue.",
                1
            ),
            ProductItem(
                "JH-WB-701",
                "Golden Quartz Tabletop Basin",
                "washbasins",
                "RAK Ceramics",
                "405 x 405 x 140 mm",
                "Metallic Glazed Ceramic",
                listOf("Exquisite brushed gold exterior", "Deep non-splattering bowl", "Single tap-hole provision"),
                "A dramatic ceramic luxury. This tabletop art basin features a brushed metallic gold outer rim contrast with a pristine black internal glaze.",
                3
            ),
            ProductItem(
                "JH-AC-801",
                "Elite Brass Angle Valve",
                "accessories",
                "Johnson Sanitary",
                "1/2 x 1/2 Inch",
                "Chrome Plated Solid Brass",
                listOf("Highly durable bronze cartridge", "Mirror finish multi-layer chrome", "Teflon leak-proof thread"),
                "Heavy-duty, precision machined ceramic disc cartridge controlling high pressure water flows safely. Resistant to mineral scales.",
                3
            ),
            ProductItem(
                "JH-FL-202",
                "Desert Travertine Clay Brick",
                "floor",
                "Nitco Tiles",
                "400 x 400 mm",
                "Terrakotta rustic finish",
                listOf("High point load weight support", "Thermal insulation backing", "Warm rustic color palette"),
                "Highly suited for high-traffic balconies, home yards, or exterior sit-outs. Beautiful traditional clay warmth.",
                2
            ),
            ProductItem(
                "JH-SW-652",
                "Glossy Tabletop Vessel Basin",
                "washbasins",
                "Somany Ceramics",
                "500 x 380 mm",
                "Pristine White Glazed Ceramic",
                listOf("Oval design profile", "Nanogloss stain protection", "Slim-wall ceramic edge"),
                "Elegant thin-walled design using advanced high-tensile clay formulation. Gives a floating deck look.",
                0
            )
        )
    }
    
    val filteredProducts = remember(selectedCategoryId, searchField) {
        products.filter { prod ->
            val matchCategory = (selectedCategoryId == "all" || prod.categoryId == selectedCategoryId)
            val matchSearch = (prod.name.contains(searchField, ignoreCase = true) || 
                               prod.id.contains(searchField, ignoreCase = true) ||
                               prod.brand.contains(searchField, ignoreCase = true) ||
                               prod.detailDescription.contains(searchField, ignoreCase = true))
            matchCategory && matchSearch
        }
    }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(CharcoalDark)
            .padding(bottom = 80.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(CharcoalDark)
                .padding(top = 12.dp, start = 20.dp, end = 20.dp, bottom = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Luxury Catalogs",
                titleNormal = "Our Premium",
                titleItalic = "Collections",
                description = "Filter and find direct specifications. Request physical samples or pricing models directly on WhatsApp."
            )
            
            OutlinedTextField(
                value = searchField,
                onValueChange = { searchField = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("Search Tiles, Closets, Brands, Finishes...", color = Color.Gray) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = GoldPrimary,
                    unfocusedBorderColor = BorderLight,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search icon",
                        tint = GoldPrimary
                    )
                },
                trailingIcon = {
                    if (searchField.isNotEmpty()) {
                        IconButton(onClick = { searchField = "" }) {
                            Icon(
                                imageVector = Icons.Filled.Close,
                                contentDescription = "Clear",
                                tint = Color.LightGray
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RoundedCornerShape(12.dp)
            )
            
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(vertical = 4.dp)
            ) {
                items(categories) { category ->
                    val isSelected = selectedCategoryId == category.id
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(if (isSelected) GoldPrimary else Color.Transparent)
                            .border(1.dp, if (isSelected) GoldPrimary else BorderLight, RoundedCornerShape(30.dp))
                            .clickable { selectedCategoryId = category.id }
                            .padding(horizontal = 16.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = category.name.uppercase(),
                            color = if (isSelected) Color.Black else Color.White,
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 9.sp,
                            letterSpacing = 1.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
        
        if (filteredProducts.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.History,
                        contentDescription = "No match",
                        tint = GoldPrimary.copy(alpha = 0.5f),
                        modifier = Modifier.size(54.dp)
                    )
                    Text(
                        text = "No premium products found",
                        color = Color.LightGray,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "Try adjusting your category filter, clearing your search query or contact us for special order materials.",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 280.dp),
                contentPadding = PaddingValues(20.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(filteredProducts) { product ->
                    ProductCardItem(
                        product = product,
                        onWhatsAppInquiry = {
                            launchWhatsAppInquiry(context, product)
                        },
                        onPhoneInquiry = {
                            try {
                                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919246351432"))
                                context.startActivity(intent)
                            } catch (ex: Exception) {
                                Toast.makeText(context, "Could not open dialer", Toast.LENGTH_SHORT).show()
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun ProductCardItem(
    product: ProductItem,
    onWhatsAppInquiry: () -> Unit,
    onPhoneInquiry: () -> Unit
) {
    var expandedDetail by remember { mutableStateOf(false) }
    
    GlassCard(
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSuperLight, RoundedCornerShape(24.dp))
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .background(Color.DarkGray)
            ) {
                ProceduralTileView(
                    styleIndex = product.previewStyle,
                    label = product.id
                )
                
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldPrimary,
                        fontSize = 8.sp,
                        letterSpacing = 1.sp
                    )
                }
            }
            
            Column(
                modifier = Modifier.padding(18.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Text(
                        text = product.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        modifier = Modifier.weight(1f)
                    )
                    
                    Spacer(modifier = Modifier.width(8.dp))
                    
                    Text(
                        text = "CODE: ${product.id.split("-").last()}",
                        style = MaterialTheme.typography.labelSmall,
                        color = GoldPrimary,
                        fontSize = 9.sp
                    )
                }
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .background(BorderLight, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "SIZE: ${product.size}",
                            fontSize = 10.sp,
                            color = Color.LightGray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                    Box(
                        modifier = Modifier
                            .background(BorderLight, RoundedCornerShape(4.dp))
                            .padding(horizontal = 8.dp, vertical = 3.dp)
                    ) {
                        Text(
                            text = "FINISH: ${product.finish.split(" ").first()}",
                            fontSize = 10.sp,
                            color = Color.LightGray,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
                
                Text(
                    text = product.detailDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray,
                    maxLines = if (expandedDetail) Int.MAX_VALUE else 2,
                    lineHeight = 16.sp,
                    modifier = Modifier.clickable { expandedDetail = !expandedDetail }
                )
                
                if (expandedDetail) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp))
                            .padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "KEY HIGHLIGHTS:",
                            style = MaterialTheme.typography.labelSmall,
                            fontSize = 8.sp,
                            color = GoldPrimary,
                            letterSpacing = 1.sp
                        )
                        product.highlights.forEach { h ->
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(4.dp)
                                        .background(GoldPrimary)
                                )
                                Text(
                                    text = h,
                                    fontSize = 11.sp,
                                    color = Color.LightGray
                                )
                            }
                        }
                    }
                }
                
                Text(
                    text = if (expandedDetail) "SHOW LESS ▲" else "SHOW DETAILS ▼",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 8.sp,
                    color = GoldPrimary,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expandedDetail = !expandedDetail }
                        .padding(vertical = 2.dp),
                    textAlign = TextAlign.Center,
                    letterSpacing = 1.sp
                )
                
                Divider(color = BorderSuperLight)
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = onPhoneInquiry,
                        modifier = Modifier
                            .border(1.dp, BorderLight, RoundedCornerShape(10.dp))
                            .height(44.dp)
                            .width(44.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Phone,
                            contentDescription = "Phone call",
                            tint = Color.LightGray,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                    
                    Button(
                        onClick = onWhatsAppInquiry,
                        modifier = Modifier
                            .weight(1f)
                            .height(44.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF25D366),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(10.dp),
                        contentPadding = PaddingValues(horizontal = 8.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Send,
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Text(
                                text = "SEND INQUIRY",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                letterSpacing = 1.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

fun launchWhatsAppInquiry(context: Context, product: ProductItem) {
    val messageTemplate = """
        Hello Jai Hanuman Tiles & Sanitary, 
        I saw this premium product on your showroom mobile catalog. These brands and materials are required for my project:
        
        Product: ${product.name}
        Code: ${product.id}
        Brand: ${product.brand}
        Dimensions: ${product.size}
        Finish: ${product.finish}
        
        Please guide me with the quote and availability. Thanks!
    """.trimIndent()
    
    val phoneNumber = "919246351432"
    
    try {
        val waUri = Uri.parse("https://api.whatsapp.com/send?phone=$phoneNumber&text=${Uri.encode(messageTemplate)}")
        val waIntent = Intent(Intent.ACTION_VIEW, waUri)
        context.startActivity(waIntent)
    } catch (e: Exception) {
        try {
            val normalSmsIntent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("smsto:$phoneNumber")
                putExtra("sms_body", messageTemplate)
            }
            context.startActivity(normalSmsIntent)
        } catch (ex: Exception) {
            Toast.makeText(context, "Could not open messaging apps", Toast.LENGTH_SHORT).show()
        }
    }
}
