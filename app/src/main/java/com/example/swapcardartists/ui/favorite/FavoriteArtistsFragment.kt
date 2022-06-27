package com.example.swapcardartists.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.swapcardartists.R
import com.example.swapcardartists.databinding.FragmentFavoriteArtistsBinding

class FavoriteArtistsFragment : Fragment() {

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

        val textView: TextView = binding.textFavorite
        favoriteArtistsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val buttonDetails: Button = binding.buttonDetails
        buttonDetails.setOnClickListener {
            val action = FavoriteArtistsFragmentDirections.actionNavigationFavoriteToNavigationDetails()
            findNavController().navigate(action)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}