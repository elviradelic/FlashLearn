package com.example.flashlearn_app.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.flashlearn_app.navigation.Screen
import com.example.flashlearn_app.viewmodel.CardViewModel
import kotlinx.coroutines.launch
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardListScreen(
    id: Int,
    deckTitle: String,
    navController: NavController,
    cardViewModel: CardViewModel = viewModel(),
    onNavigateBack: () -> Unit = {}
) {
    val backgroundColor = Color(0xFF0A1A33)
    val accentColor = Color(0xFF00A3FF)
    val deleteColor = Color(0xFFD32F2F)

    val scope = rememberCoroutineScope()

    LaunchedEffect(id, deckTitle) {
        cardViewModel.setSelectedDeck(id)
    }

    val cards by cardViewModel.cards.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = deckTitle, color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
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
                .padding(16.dp)
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.weight(1f)
            ) {
                items(cards) { card ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(card.front, fontWeight = FontWeight.Bold, color = Color.Black)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(card.back, color = Color.DarkGray)
                            Spacer(modifier = Modifier.height(12.dp))

                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                TextButton(
                                    onClick = {
                                        navController.navigate(
                                            Screen.EditCard.passArgs(
                                                card.id,
                                                card.front,
                                                card.back
                                            )
                                        )
                                    }
                                ) {
                                    Text("Edit", color = accentColor)
                                }

                                Spacer(modifier = Modifier.width(8.dp))

                                TextButton(
                                    onClick = {
                                        scope.launch {
                                            cardViewModel.deleteCard(card)
                                        }
                                    }
                                ) {
                                    Text("Delete", color = deleteColor)
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navController.navigate(Screen.AddCard.passArgs(id, deckTitle))
                },
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Add Card", color = Color.White)
            }
        }
    }
}
