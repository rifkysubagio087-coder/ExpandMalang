package com.example.expandmalang.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.expandmalang.data.model.Destination
import com.example.expandmalang.data.model.sampleDestinations
import com.example.expandmalang.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onDestinationClick: (String) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Expand Malang",
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Default.Menu, contentDescription = "Menu", tint = PrimaryGreen)
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Outlined.Notifications, contentDescription = "Notifications", tint = PrimaryGreen)
                    }
                    Box(
                        modifier = Modifier
                            .padding(end = 16.dp)
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.Gray)
                    )
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {
            item {
                SearchBar()
                PromoBanner()
                CategorySection()
                FeaturedSection(onDestinationClick)
                PopularSection()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = {},
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text("Where do you want to explore?", color = LightText) },
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = LightText) },
        trailingIcon = { 
            IconButton(onClick = {}) {
                Icon(Icons.Default.Tune, contentDescription = null, tint = PrimaryGreen)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF0F2F0),
            unfocusedContainerColor = Color(0xFFF0F2F0),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun PromoBanner() {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(180.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.LightGray)
    ) {
        AsyncImage(
            model = "https://images.pexels.com/photos/13714573/pexels-photo-13714573.jpeg",
            contentDescription = "Mount Bromo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        // Overlay gradient agar teks lebih terbaca
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f))
        )
        
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text("Mount Bromo", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.White, modifier = Modifier.size(14.dp))
                Text("Probolinggo, Malang Region", color = Color.White, fontSize = 12.sp)
            }
        }
        
        Card(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.Black.copy(alpha = 0.5f)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.WbSunny, contentDescription = null, tint = Color.White)
                Text("24°C", color = Color.White, fontWeight = FontWeight.Bold)
                Text("Clear Sky", color = Color.White, fontSize = 10.sp)
            }
        }
    }
}

@Composable
fun CategorySection() {
    val categories = listOf(
        "Mountain" to Icons.Default.Terrain,
        "Beach" to Icons.Default.BeachAccess,
        "Parks" to Icons.Default.Park,
        "Culinary" to Icons.Default.Restaurant
    )
    
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Categories", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("View All", color = PrimaryGreen, fontSize = 14.sp)
        }
        
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            categories.forEach { (name, icon) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(
                        modifier = Modifier
                            .size(56.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(LightGreenContainer),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(icon, contentDescription = name, tint = PrimaryGreen)
                    }
                    Text(name, fontSize = 12.sp, modifier = Modifier.padding(top = 4.dp))
                }
            }
        }
    }
}

@Composable
fun FeaturedSection(onDestinationClick: (String) -> Unit) {
    Column {
        Text(
            "Featured Places",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )
        LazyRow(contentPadding = PaddingValues(horizontal = 16.dp)) {
            items(sampleDestinations) { destination ->
                FeaturedCard(destination, onDestinationClick)
            }
        }
    }
}

@Composable
fun FeaturedCard(destination: Destination, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(end = 16.dp)
            .width(200.dp)
            .clickable { onClick(destination.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(modifier = Modifier.height(120.dp).fillMaxWidth()) {
                AsyncImage(
                    model = destination.imageUrl,
                    contentDescription = destination.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopEnd)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                }
                Surface(
                    modifier = Modifier.align(Alignment.BottomStart).padding(8.dp),
                    color = Color.Black.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(modifier = Modifier.padding(horizontal = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(12.dp))
                        Text(destination.rating.toString(), color = Color.White, fontSize = 10.sp)
                    }
                }
            }
            Column(modifier = Modifier.padding(12.dp)) {
                Text(destination.name, fontWeight = FontWeight.Bold)
                Text(destination.location, fontSize = 12.sp, color = LightText)
                Row(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${destination.price} / orang",
                        color = PrimaryGreen,
                        fontWeight = FontWeight.Bold
                    )
                    Button(
                        onClick = { onClick(destination.id) },
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 0.dp),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = SecondaryGreen),
                        modifier = Modifier.height(30.dp)
                    ) {
                        Text("Book", fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun PopularSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Popular Near You", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("See All", color = PrimaryGreen, fontSize = 14.sp)
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        PopularItem(
            title = "Bakso President Malang",
            desc = "Famous culinary experience near rail tracks",
            info = "Culinary • 0.8km",
            imageUrl = "https://liburananak.com/userfiles/post/5e7057483a27c.png"
        )
        PopularItem(
            title = "Kampung Warna Warni",
            desc = "Vibrant rainbow village community",
            info = "Culture • 2.5km",
            imageUrl = "https://jalankebromo.com/wp-content/uploads/2025/01/cozzyid-hotel-murah-hotel-terdekat-penginapan-murah-penginapan-terdekat-booking-hotel-kampung-warna-warni-jodipan-permata-tersembunyi-di-malang-sumber-gambar-hondacommunity.webp"
        )
    }
}

@Composable
fun PopularItem(title: String, desc: String, info: String, imageUrl: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = imageUrl,
                contentDescription = title,
                modifier = Modifier.size(60.dp).clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(start = 12.dp).weight(1f)) {
                Text(title, fontWeight = FontWeight.Bold, fontSize = 14.sp)
                Text(desc, fontSize = 12.sp, color = LightText, maxLines = 1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Restaurant, contentDescription = null, modifier = Modifier.size(12.dp), tint = PrimaryGreen)
                    Text(info, fontSize = 10.sp, color = LightText, modifier = Modifier.padding(start = 4.dp))
                }
            }
            Icon(Icons.Default.ChevronRight, contentDescription = null, tint = PrimaryGreen)
        }
    }
}
