package br.com.alex.imdbstudycase.home.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.data.model.Movie
import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.actions.OpenMovieDetailsAction
import com.bumptech.glide.Glide

class BestMoviesAdapter(
    private val activity: Activity,
    private val movies: List<Movie>,
    private val featureRouter: FeatureRouter
) : RecyclerView.Adapter<BestMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movies_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], featureRouter, activity)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewMovieBanner: ImageView = view.findViewById(R.id.imageview_movie_banner)
        private val textViewMovieTitle: TextView = view.findViewById(R.id.textview_movie_title)
        private val containerMovieListItem: LinearLayout = view.findViewById(R.id.container_movie_list_item)

        fun bind(movieDetails: Movie, featureRouter: FeatureRouter, activity: Activity) {
            Glide
                .with(activity)
                .load(movieDetails.image)
                .placeholder(R.color.shimmer_placeholder_color)
                .into(imageViewMovieBanner)

            textViewMovieTitle.text = movieDetails.title

            containerMovieListItem.setOnClickListener {
                featureRouter.start(activity, OpenMovieDetailsAction) {
                    this.putString("movie_id", movieDetails.id)
                }
            }
        }
    }
}