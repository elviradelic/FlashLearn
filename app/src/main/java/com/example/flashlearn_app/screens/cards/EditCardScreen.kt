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
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashlearn_app.viewmodel.CardViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@Composable
fun EditCardScreen(
    cardId: Int,
    front: String,
    back: String,
    onNavigateBack: () -> Unit,
    cardViewModel: CardViewModel = hiltViewModel()
) {
    val backgroundColor = Color(0xFF0A1A33)
    val accentColor = Color(0xFF00A3FF)

    var front by remember { mutableStateOf("") }
    var back by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    // Pokupi karticu iz baze kada se ekran otvori
    LaunchedEffect(cardId) {
        cardViewModel.getCardById(cardId).collectLatest { card ->
            front = card?.front ?: ""
            back = card?.back ?: ""
        }

    }

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = Color.White,
        unfocusedTextColor = Color.White,
        cursorColor = Color.White,
        focusedBorderColor = accentColor,
        unfocusedBorderColor = Color.LightGray,
        focusedLabelColor = Color.White,
        unfocusedLabelColor = Color.LightGray
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Edit Card",
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
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = back,
            onValueChange = { back = it },
            label = { Text("Answer") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = textFieldColors
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                coroutineScope.launch {
                    cardViewModel.updateCardById(cardId, front, back)
                    onNavigateBack()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = accentColor)
        ) {
            Text("Save Changes", color = Color.White)
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
