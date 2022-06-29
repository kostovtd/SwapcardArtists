package com.example.swapcardartists.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swapcardartists.R
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.databinding.FragmentFavoriteArtistsBinding
import com.example.swapcardartists.databinding.ItemArtistBinding
import com.example.swapcardartists.ui.adapters.ArtistsPagerAdapter
import com.example.swapcardartists.ui.search.SearchArtistsFragmentDirections
import kotlinx.coroutines.flow.collectLatest

class FavoriteArtistsFragment : Fragment(), ArtistsPagerAdapter.ArtistClickListener {

    private val favoriteArtistsViewModel: FavoriteArtistsViewModel by activityViewModels()

    private var _binding: FragmentFavoriteArtistsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteArtistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val artistsAdapter = ArtistsPagerAdapter()
        artistsAdapter.artistClickListener = this

        val artistsList: RecyclerView = binding.favoriteArtistsList
        artistsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = artistsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            context?.let {
                favoriteArtistsViewModel.getAllFavoriteArtists(it).collectLatest { pagingData ->
                    artistsAdapter.submitData(pagingData)
                }
            }
        }

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onArtistClicked(binding: ItemArtistBinding, artist: Artist) {
        val action =
            FavoriteArtistsFragmentDirections.actionNavigationFavoriteToNavigationDetails(artist.id)
        findNavController().navigate(action)
    }
}