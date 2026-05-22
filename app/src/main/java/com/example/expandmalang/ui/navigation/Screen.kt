package com.example.expandmalang.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CardTravel
import androidx.compose.material.icons.outlined.Explore
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector? = null,
    val unselectedIcon: ImageVector? = null
) {
    object Home : Screen("home", "Home", Icons.Filled.Home, Icons.Outlined.Home)
    object Explore : Screen("explore", "Explore", Icons.Filled.Explore, Icons.Outlined.Explore)
    object MyTrips : Screen("my_trips", "My Trips", Icons.Filled.CardTravel, Icons.Outlined.CardTravel)
    object Profile : Screen("profile", "Profile", Icons.Filled.AccountCircle, Icons.Outlined.AccountCircle)
    
    object Detail : Screen("detail/{destinationId}", "Detail") {
        fun createRoute(destinationId: String) = "detail/$destinationId"
    }
    object Form : Screen("form/{destinationId}", "Form") {
        fun createRoute(destinationId: String) = "form/$destinationId"
    }
}

val bottomNavItems = listOf(
    Screen.Home,
    Screen.Explore,
    Screen.MyTrips,
    Screen.Profile
)
