package com.example.flashlearn_app.data.dao


import androidx.room.Dao
import androidx.room.Query
import com.example.flashlearn_app.data.model.DeckEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao : BaseDao<DeckEntity> {

    @Query("SELECT * FROM decks ORDER BY id DESC")
    fun getAllDecks(): Flow<List<DeckEntity>>

    @Query("SELECT * FROM decks WHERE id = :deckId LIMIT 1")
    suspend fun getDeckById(deckId: Int): DeckEntity?
}
