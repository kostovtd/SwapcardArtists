package com.example.swapcardartists.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.repositories.ArtistsRepository
import kotlinx.coroutines.flow.Flow

class SearchArtistsViewModel : ViewModel() {

    private val artistsRepository = ArtistsRepository()

    private val _artistsFlow = artistsRepository.getAllArtists().cachedIn(viewModelScope)
    val artistsFlow: Flow<PagingData<Artist>>
        get() = _artistsFlow

}