package com.example.swapcardartists.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.databinding.ItemArtistBinding

class ArtistsPagerAdapter: PagingDataAdapter<Artist, ArtistsPagerAdapter.ArtistViewHolder>(ArtistComparator) {

    var artistClickListener: ArtistClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArtistViewHolder(
            ItemArtistBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    override fun onBindViewHolder(holder: ArtistViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }



    inner class ArtistViewHolder(private val binding: ItemArtistBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                artistClickListener?.onArtistClicked(
                    binding,
                    getItem(absoluteAdapterPosition) as Artist
                )
            }
        }

        fun bind(item: Artist)  {
            val artistName = binding.artistName
            artistName.text = item.name
        }
    }


    interface ArtistClickListener {
        fun onArtistClicked(binding: ItemArtistBinding, character: Artist)
    }


    object ArtistComparator : DiffUtil.ItemCallback<Artist>() {
        override fun areItemsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Artist, newItem: Artist) =
            oldItem.id == newItem.id
    }
}