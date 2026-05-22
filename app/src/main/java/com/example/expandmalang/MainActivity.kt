package com.example.expandmalang

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.expandmalang.ui.screens.MainScreen
import com.example.expandmalang.ui.theme.ExpandMalangTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ExpandMalangTheme {
                MainScreen()
            }
        }
    }
}
