package com.example.swapcardartists.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swapcardartists.GetAllArtistsByNameQuery
import com.example.swapcardartists.databinding.ItemArtistBinding

class ArtistsAdapter :
    RecyclerView.Adapter<ArtistsAdapter.ViewHolder>() {

    var dataSet: List<GetAllArtistsByNameQuery.Node> = emptyList()
    var artistClickListener: ArtistClickListener? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            dataSet,
            ItemArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataSet[position])
    }


    override fun getItemCount(): Int = dataSet.size

    inner class ViewHolder(
        private val dataSet: List<GetAllArtistsByNameQuery.Node>,
        private val binding: ItemArtistBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                artistClickListener?.onArtistClicked(
                    binding,
                    dataSet[absoluteAdapterPosition]
                )
            }
        }

        fun bind(item: GetAllArtistsByNameQuery.Node) {
            val artistName = binding.artistName
            artistName.text = item.fragments.artistBasicFragment.name

            val artistDetails = binding.artistDetails
            artistDetails.text = item.fragments.artistBasicFragment.disambiguation
        }
    }


    interface ArtistClickListener {
        fun onArtistClicked(binding: ItemArtistBinding, artist: GetAllArtistsByNameQuery.Node)
    }
}