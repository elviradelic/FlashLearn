package com.example.flashlearn_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.model.Deck
import com.example.flashlearn_app.data.repository.DeckRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckViewModel @Inject constructor(
    private val deckRepository: DeckRepository
) : ViewModel() {

    val decks: StateFlow<List<Deck>> = deckRepository.getAllDecks()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun insertDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.insert(deck)
        }
    }

    fun updateDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.update(deck)
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.delete(deck)
        }
    }

    suspend fun getDeckById(deckId: Int): Deck? {
        return deckRepository.getDeckById(deckId)
    }
}
