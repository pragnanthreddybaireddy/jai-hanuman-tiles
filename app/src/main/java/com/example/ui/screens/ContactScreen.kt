package com.example.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.animation.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.components.ElegantSectionHeader
import com.example.ui.components.GlassCard
import com.example.ui.components.LuxuryButton
import com.example.ui.theme.BorderLight
import com.example.ui.theme.BorderSuperLight
import com.example.ui.theme.CharcoalCard
import com.example.ui.theme.CharcoalDark
import com.example.ui.theme.GoldPrimary

@Composable
fun ContactScreen() {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    
    // Form Input States
    var clientName by remember { mutableStateOf("") }
    var clientPhone by remember { mutableStateOf("") }
    var clientEmail by remember { mutableStateOf("") }
    var productInterest by remember { mutableStateOf("Vitrified Tiles") }
    var clientMessage by remember { mutableStateOf("") }
    
    // Status states
    var submissionSuccess by remember { mutableStateOf(false) }
    var submissionError by remember { mutableStateOf<String?>(null) }
    var isSubmitting by remember { mutableStateOf(false) }
    
    val interests = remember {
        listOf("Vitrified Tiles", "Premium Slabs", "Floor Tiles", "Wall Tiles", "Water Closets", "Wash Basins", "Designer Fittings", "Other Consultation")
    }
    
    var showInterestDropdown by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 80.dp)
    ) {
        
        // 1. BRAND HERO FOR INQUIRY
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(CharcoalDark)
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                ElegantSectionHeader(
                    categoryLabel = "Virtual Showroom",
                    titleNormal = "Connect",
                    titleItalic = "Inquire Now",
                    description = "Have custom construction requirements? Fill the luxury register model below. Our showroom managers will reach out."
                )
            }
        }
        
        // 2. FORM BODY
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (submissionSuccess) {
                GlassCard(
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 20.dp,
                    borderColor = GoldPrimary
                ) {
                    Column(
                        modifier = Modifier.padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(GoldPrimary.copy(alpha = 0.15f), CircleShape)
                                .border(1.dp, GoldPrimary, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Check,
                                contentDescription = "Receipt Logged",
                                tint = GoldPrimary,
                                modifier = Modifier.size(32.dp)
                            )
                        }
                        
                        Text(
                            text = "INQUIRY LOGGED SECURELY",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            style = MaterialTheme.typography.titleMedium
                        )
                        
                        Text(
                            text = "Thank you for registering at Jai Hanuman Tiles. We have saved your preference for '$productInterest'. Direct draft copy created. You can also send the manifest via WhatsApp instantly below.",
                            color = Color.LightGray,
                            fontSize = 13.sp,
                            lineHeight = 18.sp,
                            textAlign = Alignment.CenterHorizontally.let { TextAlign.Center }
                        )
                        
                        Divider(color = BorderSuperLight, thickness = 1.dp)
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    submissionSuccess = false
                                    clientName = ""
                                    clientPhone = ""
                                    clientEmail = ""
                                    clientMessage = ""
                                },
                                modifier = Modifier.weight(1f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent,
                                    contentColor = GoldPrimary
                                ),
                                border = BorderStroke(1.dp, GoldPrimary),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text("WIPE FORM", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                            }
                            
                            Button(
                                onClick = {
                                    val formattedWhatsAppText = """
                                        Hello Jai Hanuman Tiles & Sanitary, 
                                        Registered Inquiry:
                                        Name: $clientName
                                        Phone: $clientPhone
                                        Email: ${clientEmail.ifEmpty { "N/A" }}
                                        Category Selected: $productInterest
                                        Message: $clientMessage
                                        
                                        Please send me catalogues. These brands and materials are required for my project. Thanks!
                                    """.trimIndent()
                                    try {
                                        val waIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=919246351432&text=${Uri.encode(formattedWhatsAppText)}"))
                                        context.startActivity(waIntent)
                                    } catch (ex: Exception) {
                                        Toast.makeText(context, "WhatsApp not found", Toast.LENGTH_SHORT).show()
                                    }
                                },
                                modifier = Modifier.weight(1.2f),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF25D366),
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(Icons.Filled.Send, contentDescription = null, modifier = Modifier.size(12.dp))
                                    Text("WHATSAPP SEND", fontSize = 11.sp, fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            } else {
                Text(
                    text = "REGISTER INTEREST",
                    style = MaterialTheme.typography.labelSmall,
                    color = GoldPrimary,
                    letterSpacing = 1.5.sp
                )
                
                // Name Field
                OutlinedTextField(
                    value = clientName,
                    onValueChange = { clientName = it },
                    label = { Text("Your full name *", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = BorderLight,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
                
                // Phone Field
                OutlinedTextField(
                    value = clientPhone,
                    onValueChange = { clientPhone = it },
                    label = { Text("Your mobile number *", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = BorderLight,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
                
                // Email Field
                OutlinedTextField(
                    value = clientEmail,
                    onValueChange = { clientEmail = it },
                    label = { Text("Your Email Address (Optional)", color = Color.Gray) },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = BorderLight,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp)
                )
                
                // Dropdown trigger of products Interest
                Box(modifier = Modifier.fillMaxWidth()) {
                    OutlinedTextField(
                        value = productInterest,
                        onValueChange = { },
                        readOnly = true,
                        label = { Text("Category of Interest", color = Color.Gray) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { showInterestDropdown = true },
                        trailingIcon = {
                            IconButton(onClick = { showInterestDropdown = !showInterestDropdown }) {
                                Icon(
                                    imageVector = if (showInterestDropdown) Icons.Filled.ArrowUpward else Icons.Filled.ArrowDownward,
                                    contentDescription = "Drop Menu",
                                    tint = GoldPrimary
                                )
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GoldPrimary,
                            unfocusedBorderColor = BorderLight,
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                        ),
                        shape = RoundedCornerShape(12.dp)
                    )
                    
                    DropdownMenu(
                        expanded = showInterestDropdown,
                        onDismissRequest = { showInterestDropdown = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(CharcoalCard)
                            .border(1.dp, BorderSuperLight)
                    ) {
                        interests.forEach { choice ->
                            DropdownMenuItem(
                                text = { Text(choice, color = Color.White) },
                                onClick = {
                                    productInterest = choice
                                    showInterestDropdown = false
                                }
                            )
                        }
                    }
                }
                
                // Message Field
                OutlinedTextField(
                    value = clientMessage,
                    onValueChange = { clientMessage = it },
                    label = { Text("Message / Specific dimension requirements", color = Color.Gray) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(110.dp),
                    maxLines = 4,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = GoldPrimary,
                        unfocusedBorderColor = BorderLight,
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                
                if (submissionError != null) {
                    Text(
                        text = submissionError!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                
                // Registration trigger
                Button(
                    onClick = {
                        val name = clientName.trim()
                        val phone = clientPhone.trim()
                        
                        if (name.isEmpty()) {
                            submissionError = "Please enter your name."
                            return@Button
                        }
                        if (phone.isEmpty()) {
                            submissionError = "Phone number is required."
                            return@Button
                        }
                        if (phone.length < 10) {
                            submissionError = "Enter a valid 10-digit mobile number."
                            return@Button
                        }
                        
                        // Database / Form logging Simulation
                        isSubmitting = true
                        submissionError = null
                        submissionSuccess = true
                        isSubmitting = false
                        Toast.makeText(context, "Inquiry Logged!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = GoldPrimary,
                        contentColor = Color.Black
                    )
                ) {
                    Text("REGISTER ONLINE REQUEST", fontWeight = FontWeight.Bold, letterSpacing = 0.5.sp)
                }
            }
        }
        
        // 3. PHYSICAL SHOWROOM HOURS CARD
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            ElegantSectionHeader(
                categoryLabel = "Visit Details",
                titleNormal = "Showroom",
                titleItalic = "Operating Hours"
            )
            
            Spacer(modifier = Modifier.height(14.dp))
            
            GlassCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    TimingRow(day = "Monday - Saturday", timing = "09:30 AM – 08:30 PM")
                    TimingRow(day = "Sunday", timing = "10:30 AM – 05:30 PM (Nagole Flagship)")
                    
                    Divider(color = BorderSuperLight, modifier = Modifier.padding(vertical = 4.dp))
                    
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                try {
                                    val dialIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+919246351432"))
                                    context.startActivity(dialIntent)
                                } catch (e: Exception) {
                                    Toast.makeText(context, "Could not open dialer", Toast.LENGTH_SHORT).show()
                                }
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(Icons.Filled.SupportAgent, contentDescription = null, tint = GoldPrimary, modifier = Modifier.size(16.dp))
                        Text(
                            text = "Direct Support: +91 92463 51432",
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TimingRow(day: String, timing: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = day, color = Color.LightGray, fontSize = 13.sp)
        Text(text = timing, color = GoldPrimary, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
    }
}
