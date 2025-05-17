package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardRepository {

    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    private var nextId = 1

    fun addCard(card: Card) {
        val cardWithId = card.copy(id = nextId++)
        _cards.value = _cards.value + cardWithId
    }

    fun getCardsForDeck(deckId: Int): List<Card> {
        return _cards.value.filter { it.deckId == deckId }
    }

    fun deleteCard(card: Card) {
        _cards.value = _cards.value.filterNot { it.id == card.id }
    }

    fun updateCard(cardId: Int, newFront: String, newBack: String) {
        _cards.value = _cards.value.map {
            if (it.id == cardId) it.copy(front = newFront, back = newBack) else it
        }
    }

}
