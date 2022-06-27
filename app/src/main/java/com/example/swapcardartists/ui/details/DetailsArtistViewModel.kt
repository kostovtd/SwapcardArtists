package com.example.swapcardartists.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DetailsArtistViewModel: ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is details Fragment"
    }
    val text: LiveData<String> = _text

}