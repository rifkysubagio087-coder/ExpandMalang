package com.example.expandmalang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.expandmalang.ui.theme.LightGreenContainer
import com.example.expandmalang.ui.theme.LightText
import com.example.expandmalang.ui.theme.PrimaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { 
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                        Text("Expand Malang", color = PrimaryGreen, fontWeight = FontWeight.Bold)
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                    }
                },
                actions = {
                    Box(modifier = Modifier.padding(end = 16.dp).size(32.dp).clip(CircleShape).background(Color.Gray))
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Profile Header
            Box(contentAlignment = Alignment.BottomEnd) {
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(4.dp, SecondaryGreenColor, CircleShape)
                        .background(Color.LightGray)
                )
                Surface(
                    modifier = Modifier.size(32.dp),
                    shape = CircleShape,
                    color = PrimaryGreen
                ) {
                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = Color.White,
                        modifier = Modifier.padding(6.dp)
                    )
                }
            }
            
            Spacer(Modifier.height(16.dp))
            Text("Yanto josjis", fontWeight = FontWeight.Bold, fontSize = 24.sp)
            Text("Malang Explorer • Member Sejak 2023", color = Color.Gray, fontSize = 14.sp)
            
            Spacer(Modifier.height(24.dp))
            
            // Points Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Row(
                    modifier = Modifier.padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = LightGreenContainer,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(Icons.Default.ConfirmationNumber, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.padding(12.dp))
                    }
                    Spacer(Modifier.width(16.dp))
                    Column(modifier = Modifier.weight(1f)) {
                        Text("EXPAND POINTS", fontSize = 10.sp, color = Color.Gray, fontWeight = FontWeight.Bold)
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text("2,450", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = PrimaryGreen)
                            Text(" pts", fontSize = 12.sp, color = PrimaryGreen, modifier = Modifier.padding(bottom = 2.dp))
                        }
                    }
                    Column(horizontalAlignment = Alignment.End) {
                        Text("Gold Member", fontWeight = FontWeight.Bold, fontSize = 12.sp)
                        Spacer(Modifier.height(4.dp))
                        LinearProgressIndicator(
                            progress = 0.7f,
                            modifier = Modifier.width(80.dp).height(8.dp).clip(CircleShape),
                            color = PrimaryGreen,
                            trackColor = Color(0xFFE0E0E0)
                        )
                    }
                }
            }
            
            Spacer(Modifier.height(24.dp))
            
            // Menu List
            ProfileMenuItem(Icons.Default.Person, "Informasi Pribadi")
            ProfileMenuItem(Icons.Default.Payments, "Metode Pembayaran")
            ProfileMenuItem(Icons.Default.HelpOutline, "Pusat Bantuan")
            ProfileMenuItem(Icons.Default.Gavel, "Syarat & Ketentuan")
            ProfileMenuItem(Icons.AutoMirrored.Filled.ExitToApp, "Keluar", isLogout = true)
            
            Spacer(Modifier.height(32.dp))
            Text("Versi 0.0.1 (Dalam Pengembangan)", color = Color.Gray, fontSize = 12.sp)
            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, isLogout: Boolean = false) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                color = if (isLogout) Color(0xFFFFEBEE) else Color(0xFFF1F8F5),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.size(40.dp)
            ) {
                Icon(
                    icon, 
                    contentDescription = null, 
                    tint = if (isLogout) Color.Red else PrimaryGreen, 
                    modifier = Modifier.padding(8.dp)
                )
            }
            Spacer(Modifier.width(16.dp))
            Text(
                title, 
                fontWeight = FontWeight.Medium, 
                color = if (isLogout) Color.Red else Color.Black,
                modifier = Modifier.weight(1f)
            )
            if (!isLogout) {
                Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
            }
        }
    }
}

val SecondaryGreenColor = Color(0xFF4DB680)
