package com.example.swapcardartists.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.swapcardartists.data.models.Artist
import java.io.IOException

class ArtistsPagingSource(private val artistsService: ArtistsService) :
    PagingSource<Int, Artist>() {

    override fun getRefreshKey(state: PagingState<Int, Artist>): Int = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Artist> {
        return try {
            val response = artistsService.fetchArtists()
            LoadResult.Page(
                response,
                null,
                null
                )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        }
    }
}