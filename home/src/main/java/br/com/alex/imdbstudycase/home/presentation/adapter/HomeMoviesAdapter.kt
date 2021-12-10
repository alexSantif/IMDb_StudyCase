package br.com.alex.imdbstudycase.home.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.imdbstudycase.core.navigation.MutableLiveEvent
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.data.model.Movie
import br.com.alex.imdbstudycase.home.data.model.SearchData
import br.com.alex.imdbstudycase.home.navigation.HomeDirections
import br.com.alex.imdbstudycase.home.presentation.HomeViewModel
import br.com.alex.imdbstudycase.router.model.NavigationCommand
import com.bumptech.glide.Glide

class HomeMoviesAdapter(
    private val activity: Activity,
    private val movies: List<Any>,
    private val homeViewModel: HomeViewModel
) : RecyclerView.Adapter<HomeMoviesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.movies_list_item, viewGroup, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movies[position], homeViewModel, activity)
    }

    override fun getItemCount(): Int = movies.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewMovieBanner: ImageView = view.findViewById(R.id.imageview_movie_banner)
        private val textViewMovieTitle: TextView = view.findViewById(R.id.textview_movie_title)
        private val containerMovieListItem: LinearLayout =
            view.findViewById(R.id.container_movie_list_item)

        fun bind(
            movieDetails: Any,
            homeViewModel: HomeViewModel,
            activity: Activity
        ) {
            if (movieDetails is Movie) {
                val movie = movieDetails as Movie
                Glide
                    .with(activity)
                    .load(movie.image)
                    .placeholder(R.color.shimmer_placeholder_color)
                    .into(imageViewMovieBanner)

                textViewMovieTitle.text = movie.title

                containerMovieListItem.setOnClickListener {
//                    featureRouter.start(activity, OpenMovieDetailsAction) {
//                        this.putString(MOVIE_ID_KEY, movie.id)
//                        this.putString(MOVIE_IMAGE_KEY, movie.image)
//                    }
                    homeViewModel.navigateTo()
                }
            } else if (movieDetails is SearchData) {
                val movie = movieDetails as SearchData
                Glide
                    .with(activity)
                    .load(movie.image)
                    .placeholder(R.color.shimmer_placeholder_color)
                    .into(imageViewMovieBanner)

                textViewMovieTitle.text = movie.title

                containerMovieListItem.setOnClickListener {
//                    featureRouter.start(activity, OpenMovieDetailsAction) {
//                        this.putString(MOVIE_ID_KEY, movie.id)
//                        this.putString(MOVIE_IMAGE_KEY, movie.image)
//                    }
                    homeViewModel.navigateTo()
                }
            }
        }
    }
}