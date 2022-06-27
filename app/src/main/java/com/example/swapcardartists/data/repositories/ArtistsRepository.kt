package com.example.swapcardartists.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.swapcardartists.data.ArtistsPagingSource
import com.example.swapcardartists.data.ArtistsService
import com.example.swapcardartists.data.models.Artist
import kotlinx.coroutines.flow.Flow

class ArtistsRepository {

    fun getAllArtists(): Flow<PagingData<Artist>> = Pager(
        PagingConfig(10, enablePlaceholders = false)
    ) {
        ArtistsPagingSource(ArtistsService())
    }.flow

}