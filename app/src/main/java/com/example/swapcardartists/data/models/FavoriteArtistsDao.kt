package com.example.swapcardartists.data.models

import androidx.room.*

@Dao
interface FavoriteArtistsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist): Long

    @Delete
    suspend fun delete(artist: Artist): Int

    @Query("SELECT * FROM favorite_artists")
    suspend fun getAll(): List<Artist>

    @Query("SELECT * FROM favorite_artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: String): Artist?
}