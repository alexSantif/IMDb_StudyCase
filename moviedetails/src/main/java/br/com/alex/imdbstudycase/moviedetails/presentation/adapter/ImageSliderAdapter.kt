package br.com.alex.imdbstudycase.moviedetails.presentation.adapter

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.data.model.Image
import com.bumptech.glide.Glide

class ImageSliderAdapter(
    private val activity: Activity,
    private val movieImages: MutableList<Image>,
    private val viewpagerMovieImages: ViewPager2
) : RecyclerView.Adapter<ImageSliderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_images_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieImages[position], activity)

        if (position == movieImages.size - 2) {
            viewpagerMovieImages.post(itemsRunnable)
        }
    }

    override fun getItemCount(): Int = movieImages.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageviewMovieImage: ImageView = view.findViewById(R.id.imageview_movie_image)

        fun bind(image: Image, activity: Activity) {
            Glide
                .with(activity)
                .load(image.image)
                .placeholder(R.color.shimmer_placeholder_color)
                .into(imageviewMovieImage)
        }
    }

    private val itemsRunnable = Runnable {
        movieImages.addAll(movieImages)
        notifyDataSetChanged()
    }
}