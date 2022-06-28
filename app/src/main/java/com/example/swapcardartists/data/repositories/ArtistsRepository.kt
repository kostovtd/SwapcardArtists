package com.example.swapcardartists.data.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swapcardartists.data.ArtistsLocalDataSource
import com.example.swapcardartists.data.ArtistsPagingSource
import com.example.swapcardartists.data.ArtistsService
import com.example.swapcardartists.data.models.Artist
import kotlinx.coroutines.flow.Flow

class ArtistsRepository {

    private val artistsLocalDataSource = ArtistsLocalDataSource()

    fun getAllArtists(): Flow<PagingData<Artist>> = Pager(
        PagingConfig(10, enablePlaceholders = false)
    ) {
        ArtistsPagingSource(ArtistsService())
    }.flow


    fun getAllFavoriteArtists(context: Context): Flow<PagingData<Artist>> = Pager(
        PagingConfig(10, enablePlaceholders = false)
    ) {
        artistsLocalDataSource.getAllFavoriteArtists(context)
    }.flow


    suspend fun addArtistToFavorites(context: Context, artist: Artist): Boolean {
        return artistsLocalDataSource.addArtistToFavorite(context, artist) > 0
    }


    suspend fun removeArtistFromFavorites(context: Context, artist: Artist): Boolean {
        return artistsLocalDataSource.removeArtistFromFavorite(context, artist) > 0
    }


    suspend fun isArtistInFavorites(context: Context, artist: Artist): Artist? {
        return artistsLocalDataSource.findArtistById(context, artist)
    }
}