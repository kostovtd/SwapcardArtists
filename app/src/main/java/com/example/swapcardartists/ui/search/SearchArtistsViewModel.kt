package com.example.swapcardartists.ui.search

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.repositories.ArtistsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

class SearchArtistsViewModel : ViewModel() {

    private val artistsRepository = ArtistsRepository()

    private var _artistsFlow = emptyFlow<PagingData<Artist>>()
    val artistsFlow: Flow<PagingData<Artist>>
        get() = _artistsFlow

    fun searchArtistsByName(name: String) {
        _artistsFlow = artistsRepository.searchArtistsByName(name)
    }
}