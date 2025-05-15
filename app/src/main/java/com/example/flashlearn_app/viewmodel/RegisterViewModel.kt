package com.example.flashlearn_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var nameError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)

    var showSuccess by mutableStateOf(false)

    fun onRegisterClick() {
        // Reset stanja
        nameError = false
        emailError = false
        passwordError = false
        showSuccess = false

        // Validacije
        if (fullName.length < 3) {
            nameError = true
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError = true
        }

        if (password.length < 6) {
            passwordError = true
        }

        // Ako je sve validno
        if (!nameError && !emailError && !passwordError) {
            showSuccess = true
        }
    }
}
