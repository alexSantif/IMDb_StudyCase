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
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import br.com.alex.imdbstudycase.moviedetails.presentation.adapter.ImageSliderAdapter
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
        val movieId = requireActivity().intent.getStringExtra(MOVIE_ID_KEY)

        setMovieCastList()
        getMovieDetails(movieId)
        getMovieImages(movieId)
        setCardViews()
    }

    private fun setCardViews() {
        binding.linearlayoutDescriptionHeader.setOnClickListener {
            if (binding.cardBody.visibility == VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = GONE
                binding.arrowButton.rotation = DEFAULT_ROTATION
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsDescription,
                    AutoTransition()
                )
                binding.cardBody.visibility = VISIBLE
                binding.arrowButton.rotation = ZERO_ROTATION
            }
        }

        binding.linearlayoutFullCastHeader.setOnClickListener {
            if (binding.cardBodyCast.visibility == VISIBLE) {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = GONE
                binding.arrowFullCastButton.rotation = DEFAULT_ROTATION
            } else {
                TransitionManager.beginDelayedTransition(
                    binding.cardviewDetailsCast,
                    AutoTransition()
                )
                binding.cardBodyCast.visibility = VISIBLE
                binding.arrowFullCastButton.rotation = ZERO_ROTATION
            }
        }
    }

    private fun getMovieImages(movieId: String?) {
        movieDetailsViewModel.getMovieImages(movieId)
        movieDetailsViewModel.movieImages.observe(viewLifecycleOwner, { movieImages ->
            movieImages?.items?.let { items ->
                val movieImagesList = items.subList(IMAGES_INITIAL_INDEX, IMAGES_FINAL_INDEX)
                binding.viewpagerMovieImages.adapter =
                    ImageSliderAdapter(requireActivity(), movieImagesList)
            }
        })
    }

    private fun getMovieDetails(movieId: String?) {
        movieDetailsViewModel.getMovieDetails(movieId)
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, { movieDetails ->
            movieDetails?.let { movieDetails ->
                binding.scrollviewMovieDetails.visibility = VISIBLE
                binding.containerLoadingView.visibility = GONE

                setViewsTexts(movieDetails)

                movieDetails.actorList?.let { actorList ->
                    movieCastAdapter = MovieCastAdapter(requireActivity(), actorList)
                    binding.recyclerviewMovieCast.adapter = movieCastAdapter
                }
            }
        })
    }

    private fun setViewsTexts(movieDetails: MovieDetails) {
        binding.textviewMovieTitle.text = movieDetails.title
        binding.textviewBody.text = movieDetails.plot
        binding.textviewDescriptionTitle.text =
            getString(R.string.movie_details_title, movieDetails.title)
        binding.textviewDescriptionYear.text =
            getString(R.string.movie_details_year, movieDetails.year)
        binding.textviewDescriptionDirector.text =
            getString(R.string.movie_details_director, movieDetails.directors)
    }

    private fun setMovieCastList() {
        binding.recyclerviewMovieCast.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        }
    }

    companion object {

        const val MOVIE_ID_KEY = "movie_id"
        const val DEFAULT_ROTATION = 180f
        const val ZERO_ROTATION = 0f
        const val IMAGES_INITIAL_INDEX = 0
        const val IMAGES_FINAL_INDEX = 6

        fun newInstance() = MovieDetailsFragment()
    }
}