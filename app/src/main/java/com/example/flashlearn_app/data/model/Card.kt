package com.example.flashlearn_app.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cards")
data class Card(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val front: String,
    val back: String,
    val deckId: Int,
    val deckTitle: String
)
