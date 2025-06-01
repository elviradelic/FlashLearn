package com.example.flashlearn_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val front: String,
    val back: String,
    val deckId: Int,
    val deckTitle: String
)

// Pretvori CardEntity u Card
fun CardEntity.toCard(): Card {
    return Card(
        id = id,
        front = front,
        back = back,
        deckId = deckId,
        deckTitle = deckTitle
    )
}

// Pretvori Card u CardEntity
fun Card.toEntity(): CardEntity {
    return CardEntity(
        id = this.id ?: 0, // ako je null, Room će automatski generisati ID
        front = front,
        back = back,
        deckId = deckId,
        deckTitle = deckTitle
    )
}

