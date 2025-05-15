package com.example.flashlearn_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var showSuccess by mutableStateOf(false)

    fun onLoginClick() {
        // Reset stanja
        emailError = false
        passwordError = false
        showSuccess = false

        // Validacija
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = true
        }

        if (password.length < 6) {
            passwordError = true
        }

        if (!emailError && !passwordError) {
            showSuccess = true
        }
    }
}
