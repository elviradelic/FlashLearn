package com.example.flashlearn_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.flashlearn_app.data.FlashLearnDatabase
import com.example.flashlearn_app.data.dao.UserDao
import com.example.flashlearn_app.screens.add.AddDeckScreen
import com.example.flashlearn_app.screens.cards.AddCardScreen
import com.example.flashlearn_app.screens.cards.CardListScreen
import com.example.flashlearn_app.screens.cards.EditCardScreen
import com.example.flashlearn_app.screens.details.DeckDetailScreen
import com.example.flashlearn_app.screens.home.HomeScreen
import com.example.flashlearn_app.screens.login.LoginScreen
import com.example.flashlearn_app.screens.register.RegisterScreen
import com.example.flashlearn_app.screens.splash.SplashScreen
import com.example.flashlearn_app.ui.theme.FlashLearnappTheme
import com.example.flashlearn_app.viewmodel.CardViewModel
import com.example.flashlearn_app.viewmodel.DeckViewModel
import com.example.flashlearn_app.viewmodel.LoginViewModel
import com.example.flashlearn_app.viewmodel.RegisterViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val db = FlashLearnDatabase.getDatabase(applicationContext)
        val deckDao = db.deckDao()
        val cardDao = db.cardDao()
        val userDao = db.userDao()
        setContent {
            FlashLearnappTheme {
                val navController = rememberNavController()
                val deckViewModel: DeckViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer { DeckViewModel(deckDao) }
                    }
                )
                val cardViewModel: CardViewModel = viewModel(
                    factory = viewModelFactory {
                        initializer { CardViewModel(cardDao) }
                    }
                )

                AppNavigation(navController, deckViewModel, cardViewModel, userDao)
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

    object EditCard : Screen("editCard/{cardId}/{front}/{back}") {
        fun passArgs(cardId: Int?, front: String, back: String): String =
            "editCard/${cardId ?: 0}/${front.trim()}/${back.trim()}"
    }



}

@Composable
fun AppNavigation(
    navController: NavHostController,
    deckViewModel: DeckViewModel,
    cardViewModel: CardViewModel,
    userDao: UserDao
) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {

        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }

        composable(Screen.Login.route) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = viewModelFactory { initializer { LoginViewModel(userDao) } }
            )
            LoginScreen(navController, loginViewModel)
        }

        composable(Screen.Register.route) {
            val registerViewModel: RegisterViewModel = viewModel(
                factory = viewModelFactory { initializer { RegisterViewModel(userDao) } }
            )
            RegisterScreen(navController, registerViewModel)
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
                initialFront = front,
                initialBack = back,
                cardViewModel = cardViewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
