package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.dao.DeckDao
import com.example.flashlearn_app.data.model.Deck
import com.example.flashlearn_app.data.model.toDeck
import com.example.flashlearn_app.data.model.toDeckEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DeckRepositoryImpl @Inject constructor(
    private val deckDao: DeckDao
) : DeckRepository {

    override fun getAllDecks(): Flow<List<Deck>> {
        return deckDao.getAllDecks().map { list -> list.map { it.toDeck() } }
    }

    override suspend fun getDeckById(deckId: Int): Deck? {
        return deckDao.getDeckById(deckId)?.toDeck()
    }

    override suspend fun insert(entity: Deck) {
        deckDao.insert(entity.toDeckEntity())
    }

    override suspend fun update(entity: Deck) {
        deckDao.update(entity.toDeckEntity())
    }

    override suspend fun delete(entity: Deck) {
        deckDao.delete(entity.toDeckEntity())
    }
}
