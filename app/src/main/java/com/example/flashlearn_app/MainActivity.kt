package com.example.flashlearn_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.flashlearn_app.navigation.AppNavigation
import com.example.flashlearn_app.ui.theme.FlashLearnappTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashLearnappTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
