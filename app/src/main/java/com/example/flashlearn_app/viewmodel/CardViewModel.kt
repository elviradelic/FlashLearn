package com.example.flashlearn_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.model.Card
import com.example.flashlearn_app.data.repository.CardRepository
import kotlinx.coroutines.flow.*

class CardViewModel : ViewModel() {

    private val repository = CardRepository()

    // Držimo trenutno selektovani deck ID i naziv
    private val _selectedDeckId = MutableStateFlow(0)
    private val _selectedDeckTitle = MutableStateFlow("")

    val selectedDeckTitle: StateFlow<String> = _selectedDeckTitle
    val selectedDeckId: StateFlow<Int> = _selectedDeckId

    // Filtrirani cardovi na osnovu deck ID-a
    val cards: StateFlow<List<Card>> = combine(
        repository.cards,
        _selectedDeckId
    ) { allCards, deckId ->
        allCards.filter { it.deckId == deckId }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSelectedDeck(deckId: Int, deckTitle: String) {
        _selectedDeckId.value = deckId
        _selectedDeckTitle.value = deckTitle
    }

    fun addCard(card: Card) {
        repository.addCard(card)
    }

    fun deleteCard(card: Card) {
        repository.deleteCard(card)
    }

    fun updateCard(cardId: Int, front: String, back: String) {
        repository.updateCard(cardId, front, back)
    }

}
