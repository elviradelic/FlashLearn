package com.example.flashlearn_app.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "decks")
data class DeckEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val description: String
)

fun DeckEntity.toDeck(cardCount: Int = 0): Deck {
    return Deck(
        id = id,
        title = title,
        description = description,
        cardCount = cardCount
    )
}

fun Deck.toDeckEntity(): DeckEntity {
    return DeckEntity(
        id = id,
        title = title,
        description = description
    )
}
