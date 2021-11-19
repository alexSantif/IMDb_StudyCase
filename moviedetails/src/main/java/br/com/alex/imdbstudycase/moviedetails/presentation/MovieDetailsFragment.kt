package br.com.alex.imdbstudycase.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import br.com.alex.imdbstudycase.router.FeatureRouter
import org.koin.android.ext.android.inject
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

        val movieId = requireActivity().intent.getStringExtra("movie_id")

        movieDetailsViewModel.getMovieDetails()
        movieDetailsViewModel.movieDetails.observe(viewLifecycleOwner, { movieDetails ->
            if (movieDetails != null) {
                binding.textviewMovieTitle.text = movieDetails.title
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

        binding.linearlayoutCastHeader.setOnClickListener {
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

        fun newInstance() = MovieDetailsFragment()
    }
}