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
import androidx.navigation.NavController
import com.example.flashlearn_app.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    registerViewModel: RegisterViewModel
) {
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
                value = registerViewModel.fullName,
                onValueChange = { registerViewModel.fullName = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth()
            )
            if (registerViewModel.nameError) {
                Text(
                    text = "Name must have at least 3 letters.",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = registerViewModel.email,
                onValueChange = { registerViewModel.email = it },
                label = { Text("E-mail") },
                modifier = Modifier.fillMaxWidth()
            )
            if (registerViewModel.emailError) {
                Text(
                    text = "Please enter a valid email",
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.align(Alignment.Start)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            OutlinedTextField(
                value = registerViewModel.password,
                onValueChange = { registerViewModel.password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth()
            )
            if (registerViewModel.passwordError) {
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
                    registerViewModel.onRegisterClick()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Register")
            }

            if (registerViewModel.showSuccess) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Registration successful!",
                    color = Color(0xFF2E7D32),
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(onClick = {
                navController.navigate("login")
            }) {
                Text("Already have an account? Sign up", color = Color(0xFF1976D2))
            }
        }
    }
}