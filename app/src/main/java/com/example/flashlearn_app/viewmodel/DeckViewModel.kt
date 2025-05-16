package com.example.flashlearn_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.model.Deck
import com.example.flashlearn_app.data.repository.DeckRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class DeckViewModel : ViewModel() {

    private val repository = DeckRepository()

    val decks: StateFlow<List<Deck>> = repository.decks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun addDeck(deck: Deck) {
        repository.addDeck(deck)
    }
}
