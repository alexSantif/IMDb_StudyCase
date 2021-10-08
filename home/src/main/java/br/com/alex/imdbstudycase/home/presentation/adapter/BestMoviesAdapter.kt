package br.com.alex.imdbstudycase.home.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.data.model.Movies
import com.bumptech.glide.Glide

class BestMoviesAdapter(
    private val context: Context,
    private val movies: List<Movies>
) : RecyclerView.Adapter<BestMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movies_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewMovieBanner: ImageView = view.findViewById(R.id.imageview_movie_banner)
        private val textViewMovieTitle: TextView = view.findViewById(R.id.textview_movie_title)

        fun bind(movie: Movies) {
            Glide
                .with(context)
                .load(movie.image)
                .centerCrop()
                .placeholder(android.R.drawable.editbox_background)
                .into(imageViewMovieBanner)

            textViewMovieTitle.text = movie.fullTitle
        }
    }
}