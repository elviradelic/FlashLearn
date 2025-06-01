package com.example.flashlearn_app.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.example.flashlearn_app.data.model.CardEntity
import com.example.flashlearn_app.viewmodel.CardViewModel
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.Icons

@OptIn(ExperimentalMaterial3Api::class)
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

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Add Card", color = Color.White)
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBack() }) {
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
                        val newCard = CardEntity(
                            id = 0,
                            front = front,
                            back = back,
                            deckId = deckId,
                            deckTitle = deckTitle
                        )
                        cardViewModel.insertCard(newCard)
                        onNavigateBack()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = accentColor),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Save", color = Color.White)
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = onNavigateBack,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text("Cancel", color = Color.LightGray)
            }
        }
    }
}
