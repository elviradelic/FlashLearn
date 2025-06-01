package com.example.flashlearn_app.data.repository

interface BaseRepository<T> {
    suspend fun insert(entity: T)
    suspend fun update(entity: T)
    suspend fun delete(entity: T)
}
