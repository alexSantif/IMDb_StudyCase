package br.com.alex.imdbstudycase.core.data.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM favorites_table ORDER BY id ASC")
    fun getAllMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM favorites_table WHERE movieId = :movieId")
    fun getMovieById(movieId: String?): LiveData<MovieEntity>

    @Delete
    suspend fun deleteMovie(movie: MovieEntity)
}