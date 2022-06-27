package com.example.swapcardartists.ui.search

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
import com.example.swapcardartists.databinding.FragmentSearchArtistsBinding
import com.example.swapcardartists.ui.adapters.ArtistsPagerAdapter
import kotlinx.coroutines.flow.collectLatest

class SearchArtistsFragment : Fragment() {

    private val searchArtistsViewModel: SearchArtistsViewModel by activityViewModels()

    private var _binding: FragmentSearchArtistsBinding? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchArtistsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val artistsAdapter = ArtistsPagerAdapter()

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

        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}