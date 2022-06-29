package com.example.swapcardartists.data

import com.apollographql.apollo.coroutines.await
import com.example.swapcardartists.GetAllArtistsByNameQuery
import com.example.swapcardartists.GetArtistDetailsQuery
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.models.ArtistRating

class ArtistsRemoteDataSource {

    private val artistAPI = ArtistsAPI()


    suspend fun searchArtistsByName(name: String, firstIndex: Int): ArrayList<Artist> {
        val response =
            artistAPI.getApolloClient().query(GetAllArtistsByNameQuery(name, firstIndex)).await()
        val artistsList = ArrayList<Artist>()

        response.data?.search?.artists?.nodes?.forEach { node ->
            node?.fragments?.artistBasicFragment?.let { basicFragment ->
                artistsList.add(
                    Artist(
                        basicFragment.id,
                        basicFragment.name,
                        basicFragment.disambiguation
                    )
                )
            }
        }

        return artistsList
    }


    suspend fun searchArtistById(artistId: String): Artist? {
        val response = artistAPI.getApolloClient().query(GetArtistDetailsQuery(artistId)).await()

        return response.data?.node?.fragments?.artistDetailsFragment?.let { artistDetailsFragment ->
            val name = artistDetailsFragment.name
            val id = artistId
            val description = artistDetailsFragment.disambiguation
            val country = artistDetailsFragment.country
            val type = artistDetailsFragment.type
            val fanArtUrl = artistDetailsFragment.fanArt?.backgrounds?.firstOrNull()?.url.toString()
            val rating =
                artistDetailsFragment.rating?.value?.let {
                    ArtistRating(
                        it,
                        artistDetailsFragment.rating.voteCount
                    )
                }
            Artist(id, name, description, country, type, fanArtUrl, rating)
        } ?: run {
            null
        }
    }
}