package com.example.swapcardartists.data.models

class Artist(
    val id: String,
    val name: String,
    val disambiguation: String,
    val rating: ArtistRating? = null
)

class ArtistRating(
    val value: Double,
    val voteCount: Int
)
