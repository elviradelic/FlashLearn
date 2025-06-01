package com.example.flashlearn_app.data.dao


import androidx.room.Dao
import androidx.room.Query
import com.example.flashlearn_app.data.model.CardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao : BaseDao<CardEntity> {

    @Query("SELECT * FROM cards WHERE deckId = :deckId")
    fun getCardsForDeck(deckId: Int): Flow<List<CardEntity>>

    @Query("SELECT * FROM cards WHERE id = :cardId")
    fun getCardById(cardId: Int): Flow<CardEntity?>
}
