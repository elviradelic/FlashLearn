package com.example.flashlearn_app.screens.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashlearn_app.navigation.Screen
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.activity.compose.BackHandler
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.foundation.clickable
import androidx.compose.ui.Alignment




import com.example.flashlearn_app.viewmodel.CardViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckDetailScreen(
    id: Int,
    title: String,
    description: String,
    navController: NavController,
    cardViewModel: CardViewModel = hiltViewModel()
) {
    val backgroundColor = Color(0xFF0A1A33)
    val accentColor = Color(0xFF00A3FF)

    var cardCount by remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    BackHandler(onBack = {
        navController.popBackStack()
    })


    LaunchedEffect(id) {
        scope.launch {
            cardViewModel.getCardsForDeck(id).collect {
                cardCount = it.size
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = title, color = Color.White)
                },
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable { navController.popBackStack() }
                            .padding(12.dp)
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = backgroundColor
                )
            )
        },
        containerColor = backgroundColor
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = title,
                    fontSize = 26.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = description,
                    fontSize = 16.sp,
                    color = Color.LightGray
                )

                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "This deck has $cardCount cards.",
                    color = Color.White.copy(alpha = 0.7f),
                    fontSize = 14.sp
                )
            }

            Column {
                Button(
                    onClick = {
                        navController.navigate(Screen.CardList.passArgs(id, title))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("Start Learning", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedButton(
                    onClick = {
                        navController.navigate(Screen.AddCard.passArgs(id, title))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = MaterialTheme.shapes.medium,
                    border = ButtonDefaults.outlinedButtonBorder,
                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White)
                ) {
                    Text("Add Card")
                }
            }
        }
    }
}
