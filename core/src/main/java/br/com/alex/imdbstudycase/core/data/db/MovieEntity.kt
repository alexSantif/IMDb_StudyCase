package br.com.alex.imdbstudycase.core.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 1,
    val movieId: String,
    val image: String,
    val title: String
)