package com.example.flashlearn_app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashlearn_app.data.model.CardEntity
import com.example.flashlearn_app.data.repository.CardRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@OptIn(kotlinx.coroutines.ExperimentalCoroutinesApi::class)
class CardViewModel @Inject constructor(
    private val cardRepository: CardRepository
) : ViewModel() {

    private val _selectedDeckId = MutableStateFlow(0)
    val selectedDeckId: StateFlow<Int> = _selectedDeckId

    val cards: StateFlow<List<CardEntity>> = selectedDeckId
        .flatMapLatest { deckId ->
            cardRepository.getCardsForDeck(deckId)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun setSelectedDeck(deckId: Int) {
        _selectedDeckId.value = deckId
    }

    fun insertCard(card: CardEntity) {
        viewModelScope.launch {
            cardRepository.insert(card)
        }
    }

    fun updateCard(card: CardEntity) {
        viewModelScope.launch {
            cardRepository.update(card)
        }
    }

    fun deleteCard(card: CardEntity) {
        viewModelScope.launch {
            cardRepository.delete(card)
        }
    }

    // 👇 DODATI METODA: Dohvati karticu po ID-u (za Edit ekran)
    fun getCardById(id: Int): Flow<CardEntity?> {
        return cardRepository.getCardById(id)
    }

    fun getCardsForDeck(deckId: Int): Flow<List<CardEntity>> {
        return cardRepository.getCardsForDeck(deckId)
    }

    // 👇 DODATI METODA: Ažuriraj karticu samo preko ID + novih vrijednosti
    fun updateCardById(id: Int, front: String, back: String) {
        viewModelScope.launch {
            cardRepository.getCardById(id).collect { card ->
                card?.let {
                    val updated = it.copy(front = front, back = back)
                    cardRepository.update(updated)
                }
            }
        }
    }

}
