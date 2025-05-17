package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Deck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeckRepository {

    private val _decks = MutableStateFlow<List<Deck>>(listOf(
        Deck(title = "English", description = "Map content"),
        Deck(title = "Deutsch", description = "Map content"),
        Deck(title = "French", description = "Map content")
    ))
    val decks: StateFlow<List<Deck>> = _decks

    fun addDeck(deck: Deck) {
        val nextId = (_decks.value.maxOfOrNull { it.id } ?: 0) + 1
        val newDeck = deck.copy(id = nextId)
        _decks.value = _decks.value + newDeck
    }

}

