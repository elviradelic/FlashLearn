package com.example.flashlearn_app.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.flashlearn_app.data.model.UserEntity
import com.example.flashlearn_app.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn: StateFlow<Boolean> = _isLoggedIn

    var email by mutableStateOf("")
    var password by mutableStateOf("")
    var name by mutableStateOf("")

    var emailError by mutableStateOf(false)
    var passwordError by mutableStateOf(false)
    var nameError by mutableStateOf(false)

    fun onLoginClick() {
        viewModelScope.launch {
            val user = userRepository.login(email, password)
            _isLoggedIn.value = user != null
        }
    }

    fun onRegisterClick(): Flow<Boolean> = flow {
        val success = userRepository.register(UserEntity(name = name, email = email, password = password))
        emit(success)
    }

    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? {
        return userRepository.getUserByEmailAndPassword(email, password)
    }
    fun insertUser(user: UserEntity) {
        viewModelScope.launch {
            userRepository.insert(user)
        }
    }

}
