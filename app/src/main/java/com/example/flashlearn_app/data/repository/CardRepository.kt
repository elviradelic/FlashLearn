package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.CardEntity
import kotlinx.coroutines.flow.Flow

interface CardRepository : BaseRepository<CardEntity> {
    fun getCardsForDeck(deckId: Int): Flow<List<CardEntity>>
    fun getCardById(cardId: Int): Flow<CardEntity?>
}
