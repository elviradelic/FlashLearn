package com.example.flashlearn_app.screens.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flashlearn_app.data.model.Deck

@Composable
fun AddDeckScreen(
    addDeck: (Deck) -> Unit,
    onNavigateBack: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    val backgroundColor = Color(0xFF0A1A33)
    val accentColor = Color(0xFF00A3FF)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add New Deck",
            fontSize = 22.sp,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title") },
            singleLine = true,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = backgroundColor,
                focusedContainerColor = backgroundColor,
                unfocusedBorderColor = accentColor,
                focusedBorderColor = accentColor,
                cursorColor = accentColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = accentColor,
                unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            singleLine = false,
            shape = RoundedCornerShape(12.dp),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = backgroundColor,
                focusedContainerColor = backgroundColor,
                unfocusedBorderColor = accentColor,
                focusedBorderColor = accentColor,
                cursorColor = accentColor,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = accentColor,
                unfocusedLabelColor = Color.White.copy(alpha = 0.7f)
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (title.isNotBlank() && description.isNotBlank()) {
                    addDeck(Deck(title = title, description = description))
                    onNavigateBack()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = accentColor),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text("Add Deck", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        TextButton(onClick = onNavigateBack) {
            Text("Cancel", color = Color.LightGray)
        }
    }
}
