package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Deck
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DeckRepository {

    private val _decks = MutableStateFlow<List<Deck>>(listOf(
        Deck(title = "English", description = "Map content"),
        Deck(title = "Deutsch", description = "Map content"),
        Deck(title = "Français", description = "Map content")
    ))
    val decks: StateFlow<List<Deck>> = _decks

    fun addDeck(deck: Deck) {
        _decks.value = _decks.value + deck
    }
}

