package com.example.flashlearn_app.data.repository


import com.example.flashlearn_app.data.model.UserEntity

interface UserRepository {
    suspend fun register(user: UserEntity): Boolean
    suspend fun login(email: String, password: String): UserEntity?
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?
    suspend fun insert(user: UserEntity)


}
