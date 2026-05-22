package com.example.expandmalang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
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
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import com.example.expandmalang.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTripsScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("Upcoming", "Past Trips")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My Trips", color = PrimaryGreen, fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.PhoneAndroid, contentDescription = "Device", tint = PrimaryGreen)
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {},
                containerColor = PrimaryGreen,
                contentColor = Color.White,
                shape = RoundedCornerShape(16.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Book New")
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color.White,
                contentColor = PrimaryGreen,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
                        color = PrimaryGreen
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        text = { Text(title, color = if (selectedTab == index) PrimaryGreen else Color.Gray) }
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    TripCard(
                        "Bromo Midnight",
                        "Dec 24, 2024 • 23:30",
                        "EXP-99210",
                        "Confirmed",
                        ConfirmedGreen,
                        4.9
                    )
                }
                item {
                    TripCard(
                        "Tumpak Sewu Fall",
                        "Jan 05, 2025 • 08:00",
                        "EXP-88341",
                        "Pending",
                        PendingYellow,
                        4.8
                    )
                }
                item {
                    TripCard(
                        "Batu Cultural Tour",
                        "Nov 12, 2024",
                        "",
                        "Completed",
                        CompletedGrey,
                        5.0,
                        isPast = true
                    )
                }
            }
        }
    }
}

@Composable
fun TripCard(
    title: String,
    date: String,
    bookingId: String,
    status: String,
    statusColor: Color,
    rating: Double,
    isPast: Boolean = false
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray)
            ) {
                Surface(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp),
                    color = statusColor,
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(
                        status,
                        color = Color.White,
                        fontSize = 10.sp,
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(title, fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PrimaryGreen)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                        Text(rating.toString(), fontSize = 12.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
                    }
                }
                
                Row(modifier = Modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.CalendarToday, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(14.dp))
                    Text(date, fontSize = 12.sp, color = Color.Gray, modifier = Modifier.padding(start = 4.dp))
                }
                
                Spacer(Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (bookingId.isNotEmpty()) {
                        Text("Booking ID: $bookingId", fontSize = 10.sp, color = Color.Gray)
                    } else {
                        Text("Download Invoice", fontSize = 12.sp, color = PrimaryGreen, fontWeight = FontWeight.Medium)
                    }
                    
                    Button(
                        onClick = {},
                        colors = ButtonDefaults.buttonColors(containerColor = if (isPast) LightGreenContainer else PrimaryGreen),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(if (isPast) "Review" else "Details", color = if (isPast) PrimaryGreen else Color.White, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}
