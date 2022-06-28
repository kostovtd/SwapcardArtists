package com.example.swapcardartists.data.models

import androidx.paging.PagingSource
import androidx.room.*

@Dao
interface FavoriteArtistsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArtist(artist: Artist): Long

    @Delete
    suspend fun delete(artist: Artist): Int

    @Query("SELECT * FROM favorite_artists")
    fun getAll(): PagingSource<Int, Artist>

    @Query("SELECT * FROM favorite_artists WHERE id = :artistId")
    suspend fun getArtistById(artistId: String): Artist?
}