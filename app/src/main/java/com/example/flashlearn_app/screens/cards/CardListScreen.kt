package com.example.flashlearn_app.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.example.flashlearn_app.Screen
import com.example.flashlearn_app.viewmodel.CardViewModel

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

    LaunchedEffect(id, deckTitle) {
        cardViewModel.setSelectedDeck(deckId = id, deckTitle = deckTitle)
    }

    val cards by cardViewModel.cards.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        // Top bar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onNavigateBack) {
                Text("← Back", color = accentColor, fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = deckTitle,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

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
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Navigacija ka AddCardScreen
        Button(
            onClick = {
                navController.navigate(
                    Screen.AddCard.passArgs(id, deckTitle)
                )
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Card", color = Color.White)
        }
    }
}
