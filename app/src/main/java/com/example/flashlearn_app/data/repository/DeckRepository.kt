package com.example.flashlearn_app.data.repository

import com.example.flashlearn_app.data.model.Deck
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class DeckRepository {

    private val _decks = MutableStateFlow(
        listOf(
            Deck(1, "English", "Map content", 12),
            Deck(2, "Deutsch", "Map content", 8),
            Deck(3, "Français", "Map content", 5)
        )
    )

    val decks: Flow<List<Deck>> = _decks.asStateFlow()
}
