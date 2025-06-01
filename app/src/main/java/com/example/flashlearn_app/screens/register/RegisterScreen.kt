package com.example.flashlearn_app.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.flashlearn_app.data.model.UserEntity

import com.example.flashlearn_app.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController: NavController) {

    val userViewModel: com.example.flashlearn_app.viewmodel.UserViewModel = hiltViewModel()
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var successMessage by remember { mutableStateOf<String?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFF0B1E3C), Color(0xFF1976D2))
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth()
                .background(Color.White, shape = RoundedCornerShape(20.dp))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Register",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0B1E3C)
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            if (name.length < 3) {
                Text(
                    text = "Name must have at least 3 letters.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth()
            )
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Text(
                    text = "Please enter a valid email",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (password.length < 6) {
                Text(
                    text = "The password must have at least 6 characters",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    if (name.length >= 3 &&
                        android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() &&
                        password.length >= 6
                    ) {
                        coroutineScope.launch {
                            userViewModel.insertUser(
                                UserEntity(
                                    name = name,
                                    email = email,
                                    password = password
                                )
                            )
                            successMessage = "Registration successful!"
                            errorMessage = null
                        }
                    } else {
                        errorMessage = "Please fix the form errors above."
                        successMessage = null
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

            if (successMessage != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = successMessage ?: "",
                    color = Color(0xFF2E7D32),
                    fontSize = 14.sp
                )
            }

            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = errorMessage ?: "",
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = {
                navController.navigate(Screen.Login.route)
            }) {
                Text("Already have an account? Login", color = Color(0xFF1976D2))
            }
        }
    }
}
