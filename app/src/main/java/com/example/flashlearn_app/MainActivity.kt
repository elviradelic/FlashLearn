package com.example.flashlearn_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.flashlearn_app.screens.add.AddDeckScreen
import com.example.flashlearn_app.screens.home.HomeScreen
import com.example.flashlearn_app.screens.login.LoginScreen
import com.example.flashlearn_app.screens.register.RegisterScreen
import com.example.flashlearn_app.screens.splash.SplashScreen
import com.example.flashlearn_app.ui.theme.FlashLearnappTheme
import com.example.flashlearn_app.viewmodel.DeckViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashLearnappTheme {
                val navController = rememberNavController()
                val deckViewModel: DeckViewModel = viewModel()
                AppNavigation(navController = navController, deckViewModel = deckViewModel)
            }
        }
    }
}

// Sealed class za rute 
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object AddDeck : Screen("addDeck")
}

// Navigacija
@Composable
fun AppNavigation(
    navController: NavHostController,
    deckViewModel: DeckViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }
        composable(Screen.Home.route) {
            HomeScreen(
                navController = navController,
                deckViewModel = deckViewModel
            )
        }
        composable(Screen.AddDeck.route) {
            AddDeckScreen(
                addDeck = { deckViewModel.addDeck(it) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
