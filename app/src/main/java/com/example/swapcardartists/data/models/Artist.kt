package com.example.swapcardartists.data.models

import java.io.Serializable

class Artist(
    val id: String,
    val name: String,
    val disambiguation: String,
    val country: String? = null,
    val type: String? = null,
    val rating: ArtistRating? = null
): Serializable

class ArtistRating(
    val value: Double,
    val voteCount: Int
): Serializable
