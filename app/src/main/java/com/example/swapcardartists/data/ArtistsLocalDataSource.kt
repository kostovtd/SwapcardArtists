package com.example.swapcardartists.data

import android.content.Context
import com.example.swapcardartists.data.models.Artist

class ArtistsLocalDataSource {

    suspend fun addArtistToFavorite(context: Context, artist: Artist): Long {
       return ArtistsDatabase.getInstance(context).favoriteArtistsDao().insertArtist(artist)
    }

    suspend fun removeArtistFromFavorite(context: Context, artist: Artist): Int {
        return ArtistsDatabase.getInstance(context).favoriteArtistsDao().delete(artist)
    }

    suspend fun findArtistById(context: Context, artist: Artist): Artist? {
        return ArtistsDatabase.getInstance(context).favoriteArtistsDao().getArtistById(artist.id)
    }
}