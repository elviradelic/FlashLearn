package com.example.flashlearn_app.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.dao.UserDao
import kotlinx.coroutines.launch

class LoginViewModel(private val userDao: UserDao) : ViewModel() {

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var showSuccess by mutableStateOf(false)

    fun onLoginClick() {
        emailError = false
        passwordError = false
        showSuccess = false

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) emailError = true
        if (password.length < 6) passwordError = true

        if (!emailError && !passwordError) {
            viewModelScope.launch {
                val user = userDao.login(email, password)
                showSuccess = user != null
                if (user == null) {
                    emailError = true
                    passwordError = true
                }
            }
        }
    }
}