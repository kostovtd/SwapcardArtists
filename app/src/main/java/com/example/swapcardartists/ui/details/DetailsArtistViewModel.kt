package com.example.swapcardartists.ui.details

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.repositories.ArtistsRepository
import kotlinx.coroutines.launch

class DetailsArtistViewModel: ViewModel() {

    private val artistsRepository = ArtistsRepository()

    var artist: Artist? = null

    private val _isCurrentArtistInFavorites = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isCurrentArtistInFavorites: LiveData<Boolean> = _isCurrentArtistInFavorites


    fun addCurrentArtistToFavorites(context: Context) {
        artist?.let { artist ->
            viewModelScope.launch {
                _isCurrentArtistInFavorites.value = artistsRepository.addArtistToFavorites(context, artist)
            }
        }
    }


    fun removeCurrentArtistFromFavorites(context: Context) {
        artist?.let { artist ->
            viewModelScope.launch {
                _isCurrentArtistInFavorites.value = !artistsRepository.removeArtistFromFavorites(context, artist)
            }
        }
    }


    fun isCurrentArtistInFavorites(context: Context) {
        artist?.let { artist ->
            viewModelScope.launch {
                val response = artistsRepository.isArtistInFavorites(context, artist)
                _isCurrentArtistInFavorites.value = response != null
            }
        }
    }
}