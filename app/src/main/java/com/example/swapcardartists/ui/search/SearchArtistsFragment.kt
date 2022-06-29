package com.example.swapcardartists.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swapcardartists.MainActivity
import com.example.swapcardartists.R
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.databinding.FragmentSearchArtistsBinding
import com.example.swapcardartists.databinding.ItemArtistBinding
import com.example.swapcardartists.ui.adapters.ArtistsPagerAdapter
import kotlinx.coroutines.flow.collectLatest


class SearchArtistsFragment : Fragment(), ArtistsPagerAdapter.ArtistClickListener {

    private val searchArtistsViewModel: SearchArtistsViewModel by activityViewModels()

    private lateinit var root: View

    private var _binding: FragmentSearchArtistsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)

        _binding = FragmentSearchArtistsBinding.inflate(inflater, container, false)
        root = binding.root

        val artistsAdapter = ArtistsPagerAdapter()
        artistsAdapter.artistClickListener = this

        val artistsList: RecyclerView = binding.artistsList
        artistsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = artistsAdapter
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            searchArtistsViewModel.artistsFlow.collectLatest { pagingData ->
                artistsAdapter.submitData(pagingData)
            }
        }

        searchArtistsViewModel.searchArtistsByName("Scorpions")

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_artists_menu, menu)
        val item: MenuItem = menu.findItem(R.id.action_search)
        val searchView = (activity as MainActivity?)?.supportActionBar?.themedContext
            ?.let { SearchView(it) }
        MenuItemCompat.setShowAsAction(
            item,
            MenuItemCompat.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItemCompat.SHOW_AS_ACTION_IF_ROOM
        )
        MenuItemCompat.setActionView(item, searchView)
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchArtistsViewModel.searchArtistsByName(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }


    override fun onArtistClicked(binding: ItemArtistBinding, artist: Artist) {
        val action =
            SearchArtistsFragmentDirections.actionNavigationSearchToNavigationDetails(artist.id)
        findNavController().navigate(action)
    }
}