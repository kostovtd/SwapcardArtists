package com.example.swapcardartists.ui.details

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.navigation.navArgs
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

        detailsArtistViewModel.artist = args.artist

        val textName: TextView = binding.textName
        textName.text = detailsArtistViewModel.artist?.name ?: getString(R.string.not_available)

        val textDescription: TextView = binding.textDescription
        textDescription.text =
            detailsArtistViewModel.artist?.disambiguation ?: getString(R.string.not_available)

        val textCountry: TextView = binding.textCountry
        textCountry.text =
            detailsArtistViewModel.artist?.country ?: getString(R.string.not_available)

        val textType: TextView = binding.textType
        textType.text = detailsArtistViewModel.artist?.type ?: getString(R.string.not_available)

        val textRating: TextView = binding.textRating
        textRating.text = detailsArtistViewModel.artist?.rating?.value?.toString()
            ?: getString(R.string.not_available)

        val textVotes: TextView = binding.textVotes
        textVotes.text = detailsArtistViewModel.artist?.rating?.voteCount?.toString()
            ?: getString(R.string.not_available)

        rootContainer = binding.rootContainer
        detailsArtistViewModel.isCurrentArtistInFavorites.observe(this) { isAdded ->
            toolbarMenu?.let { menu ->
                if(isAdded) {
                    menu[0].isChecked = true
                    menu[0].icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white_24dp)

                } else {
                    menu[0].isChecked = false
                    menu[0].icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_border_white_24dp)
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
                    Snackbar.make(rootContainer, R.string.artist_add_favorites_confirmation, Snackbar.LENGTH_LONG).show()
                } else {
                    detailsArtistViewModel.removeCurrentArtistFromFavorites(applicationContext)
                    Snackbar.make(rootContainer, R.string.artist_remove_favorites_confirmation, Snackbar.LENGTH_LONG).show()
                }
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}