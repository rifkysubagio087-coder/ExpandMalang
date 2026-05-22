package com.example.expandmalang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.DirectionsWalk
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage
import com.example.expandmalang.data.model.Destination
import com.example.expandmalang.data.model.sampleDestinations
import com.example.expandmalang.ui.theme.*

@Composable
fun DetailScreen(
    destinationId: String?,
    onBack: () -> Unit,
    onBook: () -> Unit
) {
    val destination = sampleDestinations.find { it.id == destinationId } ?: sampleDestinations.first()

    Scaffold(
        bottomBar = {
            BottomBookingBar(destination, onBook)
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                HeaderSection(destination, onBack)
                DestinationInfo()
                AboutSection(destination)
                FacilitiesSection()
                ReviewsSection()
                TravelInfoSection(destination)
            }
        }
    }
}

@Composable
fun HeaderSection(destination: Destination, onBack: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .background(Color.LightGray)
    ) {
        AsyncImage(
            model = destination.imageUrl,
            contentDescription = destination.name,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Overlay gradient agar teks lebih terbaca
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .statusBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(
                onClick = onBack,
                modifier = Modifier.background(Color.White.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            IconButton(
                onClick = {},
                modifier = Modifier.background(Color.White.copy(alpha = 0.3f), CircleShape)
            ) {
                Icon(Icons.Default.Share, contentDescription = "Share", tint = Color.White)
            }
        }
        
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Surface(
                color = PrimaryGreen,
                shape = RoundedCornerShape(20.dp)
            ) {
                Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text("Recommended", color = Color.White, fontSize = 10.sp)
                    Spacer(Modifier.width(4.dp))
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(10.dp))
                    Text("${destination.rating} (${destination.reviewsCount} reviews)", color = Color.White, fontSize = 10.sp)
                }
            }
            Spacer(Modifier.height(8.dp))
            Text(
                destination.name,
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                Text(destination.location, color = Color.White, fontSize = 12.sp)
            }
        }
    }
}

@Composable
fun DestinationInfo() {
    // Already covered in Header for simplicity in this mockup
}

@Composable
fun AboutSection(destination: Destination) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Tentang Destinasi", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen)
        Spacer(Modifier.height(8.dp))
        Text(
            if (destination.description.isNotEmpty()) destination.description else "Tumpak Sewu, juga dikenal sebagai Coban Sewu, adalah air terjun setinggi 120 meter yang terletak di antara Kabupaten Lumajang dan Kabupaten Malang. Air terjun ini sering dijuluki sebagai \"Niagara Indonesia\" karena formasi air terjunnya yang melingkar dan luas.",
            fontSize = 14.sp,
            color = MediumText,
            lineHeight = 20.sp
        )
    }
}

@Composable
fun FacilitiesSection() {
    val facilities = listOf(
        "Jalur Trekking" to Icons.AutoMirrored.Filled.DirectionsWalk,
        "Spot Foto" to Icons.Default.CameraAlt,
        "Parkir Luas" to Icons.Default.LocalParking,
        "Warung Lokal" to Icons.Default.Restaurant,
        "Toilet & Bilas" to Icons.Default.Wc,
        "Pemandu Lokal" to Icons.Default.Person
    )
    
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Fasilitas", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen)
        Spacer(Modifier.height(12.dp))
        
        facilities.chunked(2).forEach { rowItems ->
            Row(modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)) {
                rowItems.forEach { (name, icon) ->
                    Card(
                        modifier = Modifier.weight(1f).padding(horizontal = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8F5)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                            Icon(icon, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                            Spacer(Modifier.width(8.dp))
                            Text(name, fontSize = 12.sp, color = DarkText)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ReviewsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Ulasan Pengunjung", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen)
            Text("Lihat Semua", color = PrimaryGreen, fontSize = 12.sp)
        }
        Spacer(Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEEEEEE)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color(0xFF81C784)), contentAlignment = Alignment.Center) {
                        Text("A", color = Color.White)
                    }
                    Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                        Text("Aditya Pratama", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                        Text("2 hari yang lalu", fontSize = 12.sp, color = LightText)
                    }
                    Row {
                        repeat(5) { Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp)) }
                    }
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    "\"Pengalaman yang luar biasa! Pemandangannya benar-benar magis. Trekking ke bawah agak licin, pastikan pakai sepatu yang tepat.\"",
                    fontSize = 14.sp,
                    color = MediumText
                )
            }
        }
    }
}

@Composable
fun TravelInfoSection(destination: Destination) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F8F5)),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Informasi Perjalanan", fontWeight = FontWeight.Bold, color = PrimaryGreen)
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Schedule, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Durasi Perjalanan", fontSize = 10.sp, color = LightText)
                    Text("2.5 - 3.5 jam dari Kota Malang", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
            Spacer(Modifier.height(12.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Payments, contentDescription = null, tint = PrimaryGreen, modifier = Modifier.size(20.dp))
                Spacer(Modifier.width(8.dp))
                Column {
                    Text("Mulai Dari", fontSize = 10.sp, color = LightText)
                    Text("${destination.price}/orang", fontSize = 12.sp, fontWeight = FontWeight.Medium)
                }
            }
            Spacer(Modifier.height(16.dp))
            Box(modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(12.dp)).background(Color(0xFF003D2A))) {
                // Map placeholder
                Text("Map View", color = Color.White, modifier = Modifier.align(Alignment.Center))
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "Termasuk: Transportasi, Guide, Tiket Masuk",
                fontSize = 10.sp,
                color = LightText,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Composable
fun BottomBookingBar(destination: Destination, onBook: () -> Unit) {
    Surface(
        shadowElevation = 8.dp,
        color = Color.White
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .navigationBarsPadding(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("Total Paket Wisata", fontSize = 10.sp, color = LightText)
                Text(destination.price, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen)
            }
            Button(
                onClick = onBook,
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier.height(48.dp).weight(0.6f).padding(start = 16.dp)
            ) {
                Text("Daftar Sekarang", fontWeight = FontWeight.Bold)
            }
        }
    }
}
