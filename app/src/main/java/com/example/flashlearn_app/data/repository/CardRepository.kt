package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Card
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CardRepository {

    private val _cards = MutableStateFlow<List<Card>>(emptyList())
    val cards: StateFlow<List<Card>> = _cards

    fun addCard(card: Card) {
        _cards.value = _cards.value + card
    }

    fun getCardsForDeck(deckId: Int): List<Card> {
        return _cards.value.filter { it.deckId == deckId }
    }
}
