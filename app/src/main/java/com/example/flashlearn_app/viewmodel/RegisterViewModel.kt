package com.example.flashlearn_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.dao.UserDao
import com.example.flashlearn_app.data.model.User
import kotlinx.coroutines.launch

class RegisterViewModel(private val userDao: UserDao) : ViewModel() {

    var fullName by mutableStateOf("")
    var email by mutableStateOf("")
    var password by mutableStateOf("")

    var nameError by mutableStateOf(false)
    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)

    var showSuccess by mutableStateOf(false)

    fun onRegisterClick() {
        nameError = false
        emailError = false
        passwordError = false
        showSuccess = false

        if (fullName.length < 3) nameError = true
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) emailError = true
        if (password.length < 6) passwordError = true

        if (!nameError && !emailError && !passwordError) {
            viewModelScope.launch {
                val existing = userDao.getUserByEmail(email)
                if (existing == null) {
                    userDao.insert(User(fullName = fullName, email = email, password = password))
                    showSuccess = true
                } else {
                    emailError = true
                }
            }
        }
    }
}