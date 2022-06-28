package com.example.swapcardartists.data.models

import androidx.room.Embedded
import androidx.room.Entity
import java.io.Serializable

@Entity(
    tableName = "favorite_artists",
    primaryKeys = ["id"]
)
class Artist(
    val id: String,
    val name: String,
    val disambiguation: String,
    val country: String? = null,
    val type: String? = null,
    @Embedded val rating: ArtistRating? = null
): Serializable

class ArtistRating(
    val value: Double,
    val voteCount: Int
): Serializable
