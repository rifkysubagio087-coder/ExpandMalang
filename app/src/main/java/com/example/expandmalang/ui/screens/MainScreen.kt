package com.example.expandmalang.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.expandmalang.ui.navigation.Screen
import com.example.expandmalang.ui.navigation.bottomNavItems
import com.example.expandmalang.ui.theme.PrimaryGreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showBottomBar = bottomNavItems.any { it.route == currentDestination?.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = Color.White,
                    contentColor = PrimaryGreen
                ) {
                    bottomNavItems.forEach { screen ->
                        val selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                        NavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = if (selected) screen.selectedIcon!! else screen.unselectedIcon!!,
                                    contentDescription = screen.title
                                )
                            },
                            label = { Text(screen.title) },
                            selected = selected,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = PrimaryGreen,
                                selectedTextColor = PrimaryGreen,
                                indicatorColor = Color(0xFFE8F5E9)
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(onDestinationClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                })
            }
            composable(Screen.Explore.route) {
                ExploreScreen(onDestinationClick = { id ->
                    navController.navigate(Screen.Detail.createRoute(id))
                })
            }
            composable(Screen.MyTrips.route) {
                MyTripsScreen()
            }
            composable(Screen.Profile.route) {
                ProfileScreen()
            }
            composable(Screen.Detail.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("destinationId")
                DetailScreen(
                    destinationId = id,
                    onBack = { navController.navigateUp() },
                    onBook = { navController.navigate(Screen.Form.createRoute(id ?: "1")) }
                )
            }
            composable(Screen.Form.route) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("destinationId")
                FormScreen(
                    destinationId = id,
                    onBack = { navController.navigateUp() }
                )
            }
        }
    }
}
