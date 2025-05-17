package com.example.flashlearn_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.dao.CardDao
import com.example.flashlearn_app.data.model.Card
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CardViewModel(private val cardDao: CardDao) : ViewModel() {

    private val _selectedDeckId = MutableStateFlow(0)
    private val _selectedDeckTitle = MutableStateFlow("")

    val selectedDeckTitle: StateFlow<String> = _selectedDeckTitle
    val selectedDeckId: StateFlow<Int> = _selectedDeckId

    val cards: StateFlow<List<Card>> = _selectedDeckId
        .flatMapLatest { deckId ->
            if (deckId == 0) flowOf(emptyList())
            else cardDao.getCardsForDeck(deckId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSelectedDeck(deckId: Int, deckTitle: String) {
        _selectedDeckId.value = deckId
        _selectedDeckTitle.value = deckTitle
    }

    fun addCard(card: Card) {
        viewModelScope.launch {
            cardDao.insert(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch {
            cardDao.delete(card)
        }
    }

    fun updateCard(cardId: Int, newFront: String, newBack: String) {
        viewModelScope.launch {
            val updatedCard = cards.value.find { it.id == cardId }?.copy(front = newFront, back = newBack)
            if (updatedCard != null) {
                cardDao.update(updatedCard)
            }
        }
    }
}