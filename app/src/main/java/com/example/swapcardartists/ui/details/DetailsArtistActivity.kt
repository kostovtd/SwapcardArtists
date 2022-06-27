package com.example.swapcardartists.ui.details

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.example.swapcardartists.R
import com.example.swapcardartists.databinding.ActivityDetailsArtistBinding

class DetailsArtistActivity: AppCompatActivity() {

    private val detailsArtistViewModel: DetailsArtistViewModel by viewModels()
    private val args: DetailsArtistActivityArgs by navArgs()

    private lateinit var binding: ActivityDetailsArtistBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.details)

        detailsArtistViewModel.artist = args.artist

        val textName: TextView = binding.textName
        textName.text = detailsArtistViewModel.artist?.name ?: getString(R.string.not_available)

        val textDescription: TextView = binding.textDescription
        textDescription.text = detailsArtistViewModel.artist?.disambiguation ?: getString(R.string.not_available)

        val textCountry: TextView = binding.textCountry
        textCountry.text = detailsArtistViewModel.artist?.country ?: getString(R.string.not_available)

        val textType: TextView = binding.textType
        textType.text = detailsArtistViewModel.artist?.type ?: getString(R.string.not_available)

        val textRating: TextView = binding.textRating
        textRating.text = detailsArtistViewModel.artist?.rating?.value?.toString() ?: getString(R.string.not_available)

        val textVotes: TextView = binding.textVotes
        textVotes.text = detailsArtistViewModel.artist?.rating?.voteCount?.toString() ?: getString(R.string.not_available)
    }
}