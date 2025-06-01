package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Deck
import kotlinx.coroutines.flow.Flow

interface DeckRepository : BaseRepository<Deck> {
    fun getAllDecks(): Flow<List<Deck>>
    suspend fun getDeckById(deckId: Int): Deck?
}
