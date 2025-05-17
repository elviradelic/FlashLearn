package com.example.flashlearn_app.data.model

data class Card(
    val id: Int? = null,  // omogućava da se postavi automatski
    val front: String,
    val back: String,
    val deckId: Int,        // povezuje karticu s deckom
    val deckTitle: String
)

