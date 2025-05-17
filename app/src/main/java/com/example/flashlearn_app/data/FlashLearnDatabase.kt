package com.example.flashlearn_app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.flashlearn_app.data.model.Deck
import com.example.flashlearn_app.data.model.Card
import com.example.flashlearn_app.data.model.User
import com.example.flashlearn_app.data.dao.DeckDao
import com.example.flashlearn_app.data.dao.CardDao
import com.example.flashlearn_app.data.dao.UserDao

@Database(entities = [Deck::class, Card::class, User::class], version = 1)
abstract class FlashLearnDatabase : RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: FlashLearnDatabase? = null

        fun getDatabase(context: Context): FlashLearnDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FlashLearnDatabase::class.java,
                    "flashlearn_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}