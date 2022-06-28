package com.example.swapcardartists.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.swapcardartists.data.models.Artist
import com.example.swapcardartists.data.models.FavoriteArtistsDao

@Database(entities = [Artist::class], version = 1, exportSchema = false)
abstract class ArtistsDatabase : RoomDatabase() {

    abstract fun favoriteArtistsDao(): FavoriteArtistsDao

    companion object {
        @Volatile
        private var instance: ArtistsDatabase? = null

        fun getInstance(context: Context): ArtistsDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context,
                    ArtistsDatabase::class.java,
                    "favorite_artists_database"
                ).build().also {
                    instance = it
                }
            }
        }
    }
}