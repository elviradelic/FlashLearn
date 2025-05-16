package com.example.flashlearn_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.flashlearn_app.screens.add.AddDeckScreen
import com.example.flashlearn_app.screens.cards.AddCardScreen
import com.example.flashlearn_app.screens.cards.CardListScreen
import com.example.flashlearn_app.screens.details.DeckDetailScreen
import com.example.flashlearn_app.screens.home.HomeScreen
import com.example.flashlearn_app.screens.login.LoginScreen
import com.example.flashlearn_app.screens.register.RegisterScreen
import com.example.flashlearn_app.screens.splash.SplashScreen
import com.example.flashlearn_app.ui.theme.FlashLearnappTheme
import com.example.flashlearn_app.viewmodel.CardViewModel
import com.example.flashlearn_app.viewmodel.DeckViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FlashLearnappTheme {
                val navController = rememberNavController()
                val deckViewModel: DeckViewModel = viewModel()
                val cardViewModel: CardViewModel = viewModel()
                AppNavigation(navController, deckViewModel, cardViewModel)
            }
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home")
    object AddDeck : Screen("addDeck")

    object DeckDetail : Screen("deckDetail/{id}/{title}/{description}") {
        fun passArgs(id: Int, title: String, description: String): String =
            "deckDetail/$id/${title.trim()}/${description.trim()}"
    }

    object CardList : Screen("cardList/{id}/{deckTitle}") {
        fun passArgs(id: Int, deckTitle: String): String =
            "cardList/$id/${deckTitle.trim()}"
    }

    object AddCard : Screen("addCard/{id}/{deckTitle}") {
        fun passArgs(id: Int, deckTitle: String): String =
            "addCard/$id/${deckTitle.trim()}"
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    deckViewModel: DeckViewModel,
    cardViewModel: CardViewModel
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

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

        composable(
            route = Screen.DeckDetail.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("title") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id")
            val title = backStackEntry.arguments?.getString("title")
            val description = backStackEntry.arguments?.getString("description")

            DeckDetailScreen(
                id = id ?: 0,
                title = title ?: "",
                description = description ?: "",
                navController = navController
            )

        }

        composable(
            route = Screen.CardList.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("deckTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val deckTitle = backStackEntry.arguments?.getString("deckTitle") ?: ""

            CardListScreen(
                id = id,
                deckTitle = deckTitle,
                cardViewModel = cardViewModel,
                navController = navController,
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Screen.AddCard.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType },
                navArgument("deckTitle") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getInt("id") ?: 0
            val deckTitle = backStackEntry.arguments?.getString("deckTitle") ?: ""

            AddCardScreen(
                deckId = id,
                deckTitle = deckTitle,
                cardViewModel = cardViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
