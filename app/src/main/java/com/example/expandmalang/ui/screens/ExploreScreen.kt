package com.example.expandmalang.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.expandmalang.data.model.Destination
import com.example.expandmalang.data.model.sampleDestinations
import com.example.expandmalang.ui.theme.PrimaryGreen
import com.example.expandmalang.ui.theme.SecondaryGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(onDestinationClick: (String) -> Unit) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Map Background Placeholder
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE0E0E0))
        ) {
            // Simulated Map Content
            Icon(
                Icons.Default.LocationOn,
                contentDescription = null,
                tint = PrimaryGreen,
                modifier = Modifier.size(48.dp).align(Alignment.Center).offset(x = 50.dp, y = (-100).dp)
            )
            Surface(
                modifier = Modifier.align(Alignment.Center).offset(x = 80.dp, y = (-130).dp),
                color = PrimaryGreen,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Mount Bromo View", color = Color.White, modifier = Modifier.padding(4.dp), fontSize = 10.sp)
            }
        }

        Column {
            // Top Bar
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(Color.White, CircleShape)
                ) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = PrimaryGreen)
                }
                Text(
                    "Expand Malang",
                    modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = PrimaryGreen
                )
                IconButton(
                    onClick = { },
                    modifier = Modifier.background(Color.White, CircleShape)
                ) {
                    Icon(Icons.Default.FilterList, contentDescription = "Filter", tint = PrimaryGreen)
                }
            }

            // Search Bar
            SearchBarPlaceholder()
        }

        // Bottom List
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        ) {
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(sampleDestinations) { destination ->
                    ExploreCard(destination, onDestinationClick)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarPlaceholder() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(30.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(Icons.Default.Search, contentDescription = null, tint = Color.Gray)
            Spacer(Modifier.width(8.dp))
            Text("Explore Malang's hidden gems...", color = Color.Gray, modifier = Modifier.weight(1f))
            Icon(Icons.Default.Mic, contentDescription = null, tint = PrimaryGreen)
        }
    }
}

@Composable
fun ExploreCard(destination: Destination, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .width(280.dp)
            .height(320.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Box(modifier = Modifier.weight(1f).fillMaxWidth()) {
                AsyncImage(
                    model = destination.imageUrl,
                    contentDescription = destination.name,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                IconButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.TopEnd).padding(8.dp).background(Color.White.copy(alpha = 0.3f), CircleShape)
                ) {
                    Icon(Icons.Default.FavoriteBorder, contentDescription = null, tint = Color.White)
                }
                Row(
                    modifier = Modifier.align(Alignment.BottomStart).padding(12.dp).background(Color.Black.copy(alpha = 0.3f), RoundedCornerShape(8.dp)).padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Star, contentDescription = null, tint = Color.Yellow, modifier = Modifier.size(16.dp))
                    Text(destination.rating.toString(), color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(start = 4.dp))
                    Text(" (1.2k reviews)", color = Color.White, fontSize = 12.sp)
                }
            }
            Column(modifier = Modifier.padding(16.dp)) {
                Text(destination.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(destination.location, fontSize = 14.sp, color = Color.Gray)
                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { onClick(destination.id) },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryGreen),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("View Details")
                }
            }
        }
    }
}
