package com.example.swapcardartists.ui.favorite

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.repositories.ArtistsRepository
import kotlinx.coroutines.flow.Flow

class FavoriteArtistsViewModel : ViewModel() {

    private val artistsRepository = ArtistsRepository()


    fun getAllFavoriteArtists(context: Context) = artistsRepository.getAllFavoriteArtists(context).cachedIn(viewModelScope)
}