package br.com.alex.imdbstudycase.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import br.com.alex.imdbstudycase.moviedetails.presentation.adapter.MovieCastAdapter
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    private lateinit var binding: FragmentMovieDetailsBinding

    private lateinit var movieCastAdapter: MovieCastAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMovieDetailsBinding.bind(view)

        binding.recyclerviewMovieCast.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }

        val movieId = requireActivity().intent.getStringExtra(MOVIE_ID_KEY)
        val movieImage = requireActivity().intent.getStringExtra(MOVIE_IMAGE_KEY)

        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, { movieDetails ->
            if (movieDetails != null) {
                binding.scrollviewMovieDetails.visibility = VISIBLE
                binding.containerLoadingView.visibility = GONE
                binding.textviewMovieTitle.text = movieDetails.title
                binding.textviewBody.text = movieDetails.plot
                binding.textviewDescriptionTitle.text =
                    getString(R.string.movie_details_title, movieDetails.title)
                binding.textviewDescriptionYear.text =
                    getString(R.string.movie_details_year, movieDetails.year)
                binding.textviewDescriptionDirector.text =
                    getString(R.string.movie_details_director, movieDetails.directors)

                Glide
                    .with(requireActivity())
                    .load(movieImage)
                    .placeholder(R.color.shimmer_placeholder_color)
                    .into(binding.imageviewMovieDetails)

                movieDetails.actorList?.let { actorList ->
                    movieCastAdapter = MovieCastAdapter(requireActivity(), actorList)
                    binding.recyclerviewMovieCast.adapter = movieCastAdapter
                }
            }
        })

        binding.linearlayoutDescriptionHeader.setOnClickListener {
            if (binding.cardBody.visibility == VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = GONE
                binding.arrowButton.rotation = 180f
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = VISIBLE
                binding.arrowButton.rotation = 0f
            }
        }

        binding.linearlayoutFullCastHeader.setOnClickListener {
            if (binding.cardBodyCast.visibility == VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = GONE
                binding.arrowFullCastButton.rotation = 180f
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = VISIBLE
                binding.arrowFullCastButton.rotation = 0f
            }
        }
    }

    companion object {

        const val MOVIE_ID_KEY = "movie_id"
        const val MOVIE_IMAGE_KEY = "movie_image"

        fun newInstance() = MovieDetailsFragment()
    }
}