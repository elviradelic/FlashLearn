package com.example.flashlearn_app.data.dao

import androidx.room.*
import com.example.flashlearn_app.data.model.Card
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {
    @Query("SELECT * FROM cards WHERE deckId = :deckId")
    fun getCardsForDeck(deckId: Int): Flow<List<Card>>

    @Insert
    suspend fun insert(card: Card): Long

    @Delete
    suspend fun delete(card: Card)

    @Update
    suspend fun update(card: Card)
}