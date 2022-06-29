package com.example.swapcardartists.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.example.swapcardartists.R
import com.example.swapcardartists.databinding.ActivityDetailsArtistBinding
import com.google.android.material.snackbar.Snackbar

class DetailsArtistActivity : AppCompatActivity() {

    private val detailsArtistViewModel: DetailsArtistViewModel by viewModels()
    private val args: DetailsArtistActivityArgs by navArgs()

    private lateinit var binding: ActivityDetailsArtistBinding
    private var toolbarMenu: Menu? = null
    private lateinit var rootContainer: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsArtistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = getString(R.string.details)

        detailsArtistViewModel.artistId = args.artistId

        val artistImage: ImageView = binding.artistImage
        val textName: TextView = binding.textName
        val textDescription: TextView = binding.textDescription
        val textCountry: TextView = binding.textCountry
        val textType: TextView = binding.textType
        val textRating: TextView = binding.textRating
        val textVotes: TextView = binding.textVotes

        detailsArtistViewModel.artistId?.let {
            detailsArtistViewModel.searchArtistById(it)
        }

        detailsArtistViewModel.selectedArtist.observe(this) {
            Glide.with(this).load(it?.fanArtUrl).into(artistImage)

            textName.text = it?.name ?: getString(R.string.not_available)

            textDescription.text = it?.disambiguation ?: getString(R.string.not_available)

            textCountry.text = it?.country ?: getString(R.string.not_available)

            textType.text = it?.type ?: getString(R.string.not_available)

            textRating.text = it?.rating?.value?.toString()
                ?: getString(R.string.not_available)

            textVotes.text = it?.rating?.voteCount?.toString()
                ?: getString(R.string.not_available)
        }


        rootContainer = binding.rootContainer

        detailsArtistViewModel.isCurrentArtistInFavorites.observe(this) { isAdded ->
            toolbarMenu?.let { menu ->
                if (isAdded) {
                    menu[0].isChecked = true
                    menu[0].icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)

                } else {
                    menu[0].isChecked = false
                    menu[0].icon =
                        ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
                }
            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.artist_details_menu, menu)
        toolbarMenu = menu
        detailsArtistViewModel.isCurrentArtistInFavorites(applicationContext)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_add_to_favorite -> {
                item.isChecked = !item.isChecked
                if (item.isChecked) {
                    detailsArtistViewModel.addCurrentArtistToFavorites(applicationContext)
                    Snackbar.make(
                        rootContainer,
                        R.string.artist_add_favorites_confirmation,
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    detailsArtistViewModel.removeCurrentArtistFromFavorites(applicationContext)
                    Snackbar.make(
                        rootContainer,
                        R.string.artist_remove_favorites_confirmation,
                        Snackbar.LENGTH_LONG
                    ).show()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}