package com.example.swapcardartists.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.swapcardartists.databinding.ActivityDetailsArtistBinding

class DetailsArtistActivity: AppCompatActivity() {

    private val detailsArtistViewModel: DetailsArtistViewModel by viewModels()

    private lateinit var binding: ActivityDetailsArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val textView: TextView = binding.textDetails
        detailsArtistViewModel.text.observe(this) {
            textView.text = it
        }
    }
}