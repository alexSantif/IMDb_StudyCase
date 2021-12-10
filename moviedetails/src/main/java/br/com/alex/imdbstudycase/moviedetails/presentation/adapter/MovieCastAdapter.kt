package br.com.alex.imdbstudycase.moviedetails.presentation.adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.data.model.Actor
import com.bumptech.glide.Glide

class MovieCastAdapter(
    private val activity: Activity,
    private val movieCast: List<Actor>
) : RecyclerView.Adapter<MovieCastAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.cast_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieCast[position], activity)
    }

    override fun getItemCount(): Int = movieCast.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageViewCastCharacter: ImageView = view.findViewById(R.id.imageview_cast_character)
        private val textViewCastName: TextView = view.findViewById(R.id.textview_cast_name)

        fun bind(movieCast: Actor, activity: Activity) {
            Glide
                .with(activity)
                .load(movieCast.image)
                .placeholder(R.color.shimmer_placeholder_color)
                .into(imageViewCastCharacter)

            textViewCastName.text = movieCast.name
        }
    }
}