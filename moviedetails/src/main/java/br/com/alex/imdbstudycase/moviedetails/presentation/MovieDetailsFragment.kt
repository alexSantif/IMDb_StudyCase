package br.com.alex.imdbstudycase.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    private lateinit var binding: FragmentMovieDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        val movieId = requireActivity().intent.getStringExtra(MOVIE_ID_KEY)
        val movieImage = requireActivity().intent.getStringExtra(MOVIE_IMAGE_KEY)

        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, { movieDetails ->
            if (movieDetails != null) {
                binding.textviewMovieTitle.text = movieDetails.title
                binding.textviewBody.text = movieDetails.plot
                binding.textviewDescriptionTitle.text =
                    getString(R.string.movie_details_title, movieDetails.title)
                binding.textviewDescriptionYear.text = movieDetails.year
                binding.textviewDescriptionDirector.text = movieDetails.directors

                Glide
                    .with(requireActivity())
                    .load(movieImage)
                    .placeholder(R.color.shimmer_placeholder_color)
                    .into(binding.imageviewMovieDetails)
            }
        })

        binding.linearlayoutDescriptionHeader.setOnClickListener {
            if (binding.cardBody.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = View.GONE
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = View.VISIBLE
            }
        }

        binding.linearlayoutFullCastHeader.setOnClickListener {
            if (binding.cardBodyCast.visibility == View.VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = View.GONE
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = View.VISIBLE
            }
        }
    }

    companion object {

        const val MOVIE_ID_KEY = "movie_id"
        const val MOVIE_IMAGE_KEY = "movie_image"

        fun newInstance() = MovieDetailsFragment()
    }
}