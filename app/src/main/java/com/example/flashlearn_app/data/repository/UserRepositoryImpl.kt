package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.dao.UserDao
import com.example.flashlearn_app.data.model.UserEntity
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override suspend fun register(user: UserEntity): Boolean {
        val existingUser = userDao.getUserByEmail(user.email)
        return if (existingUser == null) {
            userDao.insert(user)
            true
        } else {
            false
        }
    }

    override suspend fun login(email: String, password: String): UserEntity? {
        return userDao.authenticateUser(email, password)
    }
    override suspend fun getUserByEmailAndPassword(email: String, password: String): UserEntity? {
        return userDao.getUserByEmailAndPassword(email, password)
    }
    override suspend fun insert(user: UserEntity) {
        userDao.insert(user)
    }


}
