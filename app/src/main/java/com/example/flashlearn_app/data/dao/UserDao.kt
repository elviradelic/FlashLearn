package com.example.flashlearn_app.data.dao

import androidx.room.*
import com.example.flashlearn_app.data.model.UserEntity

@Dao
interface UserDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM users WHERE email = :email")
    suspend fun getUserByEmail(email: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun authenticateUser(email: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE email = :email AND password = :password LIMIT 1")
    suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity?




}
