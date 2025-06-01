package com.example.flashlearn_app.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flashlearn_app.screens.splash.SplashScreen

import com.example.flashlearn_app.screens.cards.AddCardScreen
import com.example.flashlearn_app.screens.cards.CardListScreen
import com.example.flashlearn_app.screens.cards.EditCardScreen
import com.example.flashlearn_app.screens.home.HomeScreen
import com.example.flashlearn_app.viewmodel.CardViewModel
import com.example.flashlearn_app.viewmodel.DeckViewModel
import com.example.flashlearn_app.screens.add.AddDeckScreen
import com.example.flashlearn_app.screens.login.LoginScreen
import com.example.flashlearn_app.screens.register.RegisterScreen
import com.example.flashlearn_app.screens.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object AddDeck : Screen("addDeck")

    object AddCard : Screen("addCard/{deckId}/{deckTitle}") {
        fun passArgs(deckId: Int, deckTitle: String) = "addCard/$deckId/$deckTitle"
    }

    object CardList : Screen("cardList/{deckId}/{deckTitle}") {
        fun passArgs(deckId: Int, deckTitle: String) = "cardList/$deckId/$deckTitle"
    }

    object EditCard : Screen("editCard/{cardId}/{front}/{back}") {
        fun passArgs(cardId: Int, front: String, back: String): String {
            return "editCard/$cardId/$front/$back"
        }
    }
}

@Composable
fun AppNavigation(navController: NavHostController) {
    val deckViewModel: DeckViewModel = hiltViewModel()
    val cardViewModel: CardViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        // Splash
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        // Login
        composable(Screen.Login.route) {
            LoginScreen(navController)
        }

        // Register
        composable(Screen.Register.route) {
            RegisterScreen(navController)
        }

        // Home
        composable(Screen.Home.route) {
            HomeScreen(navController, deckViewModel)
        }

        // Add Deck
        composable(Screen.AddDeck.route) {
            AddDeckScreen(
                onNavigateBack = { navController.popBackStack() },
                deckViewModel = deckViewModel
            )
        }

        // Card List
        composable(
            route = Screen.CardList.route,
            arguments = listOf(
                navArgument("deckId") { type = NavType.IntType },
                navArgument("deckTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getInt("deckId") ?: 0
            val deckTitle = backStackEntry.arguments?.getString("deckTitle") ?: ""
            CardListScreen(
                id = deckId,
                deckTitle = deckTitle,
                navController = navController,
                cardViewModel = cardViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // Add Card
        composable(
            route = Screen.AddCard.route,
            arguments = listOf(
                navArgument("deckId") { type = NavType.IntType },
                navArgument("deckTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val deckId = backStackEntry.arguments?.getInt("deckId") ?: 0
            val deckTitle = backStackEntry.arguments?.getString("deckTitle") ?: ""
            AddCardScreen(
                deckId = deckId,
                deckTitle = deckTitle,
                onNavigateBack = { navController.popBackStack() },
                cardViewModel = cardViewModel
            )
        }

        // Edit Card
        composable(
            route = Screen.EditCard.route,
            arguments = listOf(
                navArgument("cardId") { type = NavType.IntType },
                navArgument("front") { type = NavType.StringType },
                navArgument("back") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val cardId = backStackEntry.arguments?.getInt("cardId") ?: 0
            val front = backStackEntry.arguments?.getString("front") ?: ""
            val back = backStackEntry.arguments?.getString("back") ?: ""
            EditCardScreen(
                cardId = cardId,
                front = front,
                back = back,
                onNavigateBack = { navController.popBackStack() },
                cardViewModel = cardViewModel
            )
        }
    }
}
