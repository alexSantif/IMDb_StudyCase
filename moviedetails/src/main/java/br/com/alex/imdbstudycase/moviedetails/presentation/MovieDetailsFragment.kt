package br.com.alex.imdbstudycase.moviedetails.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import br.com.alex.imdbstudycase.router.FeatureRouter
import org.koin.android.ext.android.inject

class MovieDetailsFragment : Fragment() {

    private val featureRouter: FeatureRouter by inject()

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
        @JvmStatic
        fun newInstance() = MovieDetailsFragment()
    }
}