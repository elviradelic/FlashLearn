package com.example.flashlearn_app.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flashlearn_app.data.model.Card
import com.example.flashlearn_app.viewmodel.CardViewModel

@Composable
fun AddCardScreen(
    deckId: Int,
    deckTitle: String,
    onNavigateBack: () -> Unit,
    cardViewModel: CardViewModel = viewModel()
) {
    val backgroundColor = Color(0xFF0A1A33)
    val accentColor = Color(0xFF00A3FF)

    var front by remember { mutableStateOf("") }
    var back by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Add Card to \"$deckTitle\"",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = front,
            onValueChange = { front = it },
            label = { Text("Question") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = back,
            onValueChange = { back = it },
            label = { Text("Answer") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = backgroundColor,
                unfocusedContainerColor = backgroundColor,
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.White,
                cursorColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (front.isNotBlank() && back.isNotBlank()) {
                    val newCard = Card(
                        id = 0,
                        front = front,
                        back = back,
                        deckId = deckId,
                        deckTitle = deckTitle
                    )
                    cardViewModel.addCard(newCard)
                    onNavigateBack()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save", color = Color.White)
        }

        Spacer(modifier = Modifier.height(12.dp))

        TextButton(onClick = onNavigateBack, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Cancel", color = Color.LightGray)
        }
    }
}
