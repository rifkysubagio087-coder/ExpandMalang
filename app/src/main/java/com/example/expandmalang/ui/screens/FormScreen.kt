package com.example.expandmalang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expandmalang.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormScreen(
    destinationId: String?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Expand Malang", color = PrimaryGreen, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(end = 16.dp).size(32.dp).clip(CircleShape).background(Color.Gray))
                }
            )
        },
        bottomBar = {
            Button(
                onClick = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(56.dp)
                    .navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Konfirmasi Pendaftaran", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                    Spacer(Modifier.width(8.dp))
                    Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(18.dp))
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Destination Summary
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth().height(150.dp).background(Color.LightGray)) {
                        Column(modifier = Modifier.align(Alignment.BottomStart).padding(16.dp)) {
                            Text("Bromo Midnight Adventure", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                                Text("Tengger Semeru National Park", color = Color.White, fontSize = 10.sp)
                            }
                        }
                    }
                    Row(
                        modifier = Modifier.padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text("ESTIMATED TOTAL", fontSize = 10.sp, color = LightText)
                            Text("Rp 1.250.000 / trip", fontWeight = FontWeight.Bold, color = PrimaryGreen)
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                            Text("4.9", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                            Text(" 2k+ reviews", color = LightText, fontSize = 10.sp, modifier = Modifier.padding(start = 4.dp))
                        }
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
            Text("Registration Details", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("Please fill in the information below to secure your spot for the adventure.", color = LightText, fontSize = 12.sp)
            
            Spacer(Modifier.height(24.dp))
            
            FormField(label = "Full Name", placeholder = "Enter your full name", icon = Icons.Default.Person)
            FormField(label = "Number of Visitors", placeholder = "1 Person", icon = Icons.Default.Groups, isDropdown = true)
            FormField(label = "Visit Date", placeholder = "mm/dd/yyyy", icon = Icons.Default.CalendarToday)
            FormField(label = "Contact Information (Email/WhatsApp)", placeholder = "yanto@example.com", icon = Icons.Default.AlternateEmail)
            FormField(label = "Special Requests (Optional)", placeholder = "Dietary requirements, pickup point, etc.", isMultiline = true)

            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(vertical = 16.dp)) {
                Checkbox(checked = false, onCheckedChange = {})
                Text(
                    "I agree to the Terms and Conditions and understand the safety protocols for high-altitude trekking.",
                    fontSize = 12.sp,
                    color = LightText
                )
            }
            Spacer(Modifier.height(80.dp))
        }
    }
}

@Composable
fun FormField(
    label: String,
    placeholder: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector? = null,
    isDropdown: Boolean = false,
    isMultiline: Boolean = false
) {
    Column(modifier = Modifier.padding(bottom = 16.dp)) {
        Text(label, fontWeight = FontWeight.Medium, fontSize = 14.sp, modifier = Modifier.padding(bottom = 8.dp))
        OutlinedTextField(
            value = "",
            onValueChange = {},
            modifier = Modifier.fillMaxWidth(),
            placeholder = { Text(placeholder, color = Color.Gray, fontSize = 14.sp) },
            leadingIcon = icon?.let { { Icon(it, contentDescription = null, tint = Color.Gray) } },
            trailingIcon = if (isDropdown) { { Icon(Icons.Default.ExpandMore, contentDescription = null) } } else null,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = Color(0xFFE0E0E0),
                unfocusedContainerColor = Color(0xFFF9FAFB),
                focusedContainerColor = Color(0xFFF9FAFB)
            ),
            minLines = if (isMultiline) 3 else 1
        )
    }
}
