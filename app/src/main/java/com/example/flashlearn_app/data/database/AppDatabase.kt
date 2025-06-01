package com.example.flashlearn_app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashlearn_app.data.dao.CardDao
import com.example.flashlearn_app.data.dao.DeckDao
import com.example.flashlearn_app.data.dao.UserDao
import com.example.flashlearn_app.data.model.CardEntity
import com.example.flashlearn_app.data.model.DeckEntity
import com.example.flashlearn_app.data.model.UserEntity

@Database(
    entities = [CardEntity::class, DeckEntity::class, UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao
    abstract fun deckDao(): DeckDao
    abstract fun userDao(): UserDao
}
