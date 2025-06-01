package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.dao.CardDao
import com.example.flashlearn_app.data.model.CardEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
    private val cardDao: CardDao
) : CardRepository {

    override suspend fun insert(entity: CardEntity) {
        cardDao.insert(entity)
    }

    override suspend fun update(entity: CardEntity) {
        cardDao.update(entity)
    }

    override suspend fun delete(entity: CardEntity) {
        cardDao.delete(entity)
    }

    override fun getCardsForDeck(deckId: Int): Flow<List<CardEntity>> {
        return cardDao.getCardsForDeck(deckId)
    }

    override fun getCardById(cardId: Int): Flow<CardEntity?> {
        return cardDao.getCardById(cardId)
    }
}
