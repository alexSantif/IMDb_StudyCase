package br.com.alex.imdbstudycase.moviedetails.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.AutoTransition
import androidx.transition.TransitionManager
import androidx.viewpager2.widget.ViewPager2
import br.com.alex.imdbstudycase.core.data.db.MovieEntity
import br.com.alex.imdbstudycase.moviedetails.R
import br.com.alex.imdbstudycase.moviedetails.data.model.Image
import br.com.alex.imdbstudycase.moviedetails.data.model.MovieDetails
import br.com.alex.imdbstudycase.moviedetails.databinding.FragmentMovieDetailsBinding
import br.com.alex.imdbstudycase.moviedetails.presentation.adapter.ImageSliderAdapter
import br.com.alex.imdbstudycase.moviedetails.presentation.adapter.MovieCastAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : Fragment() {

    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()

    private lateinit var binding: FragmentMovieDetailsBinding

    private lateinit var movieCastAdapter: MovieCastAdapter
    private val movieImagesHandler: Handler = Handler(Looper.getMainLooper())
    private var isMovieFavorite = false
    private var movieEntity: MovieEntity? = null

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
        validateIsMovieFavorite(movieId)

        binding.buttonFavoriteMovie.setOnClickListener {
            if (isMovieFavorite) {
                val favoriteImageView = it as ImageView
                favoriteImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.darker_gray
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                movieDetailsViewModel.deleteMovie(movieEntity)
            } else {
                val favoriteImageView = it as ImageView
                favoriteImageView.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.holo_red_dark
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
                val movieEntityTesting = MovieEntity(
                    movieId = movieId!!,
                    image = "",
                    title = "testando"
                )
                movieDetailsViewModel.addMovie(movieEntityTesting)
            }
        }
    }

    private fun validateIsMovieFavorite(movieId: String?) {
        movieDetailsViewModel.validateIsMovieFavorite(movieId)
        movieDetailsViewModel.favoriteMovie.observe(viewLifecycleOwner, { favoriteMovie ->
            favoriteMovie?.let {
                isMovieFavorite = true
                movieEntity = it[0]
                binding.buttonFavoriteMovie.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.holo_red_dark
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
            } ?: let {
                isMovieFavorite = false
                binding.buttonFavoriteMovie.setColorFilter(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.darker_gray
                    ), android.graphics.PorterDuff.Mode.SRC_IN
                )
            }
        })
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
                binding.circularImagesProgressBar.visibility = GONE
                if (items.size >= IMAGES_FINAL_INDEX) {
                    val movieImagesList = items.subList(IMAGES_INITIAL_INDEX, IMAGES_FINAL_INDEX)
                    setMovieImagesSlider(movieImagesList)
                } else {
                    val movieImagesList = items.subList(IMAGES_INITIAL_INDEX, items.size)
                    setMovieImagesSlider(movieImagesList)
                }
            }
        })
    }

    private fun setMovieImagesSlider(movieImagesList: List<Image>) {
        binding.viewpagerMovieImages.adapter =
            ImageSliderAdapter(
                requireActivity(),
                movieImagesList.toMutableList(),
                binding.viewpagerMovieImages
            )

        binding.viewpagerMovieImages.clipToPadding = false
        binding.viewpagerMovieImages.clipChildren = false
        binding.viewpagerMovieImages.offscreenPageLimit = 3
        binding.viewpagerMovieImages.getChildAt(0).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        binding.viewpagerMovieImages.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                movieImagesHandler.removeCallbacks(movieImagesRunnable)
                movieImagesHandler.postDelayed(movieImagesRunnable, 3000)
            }
        }
        )
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

    override fun onPause() {
        super.onPause()
        movieImagesHandler.removeCallbacks(movieImagesRunnable)
    }

    override fun onResume() {
        super.onResume()
        movieImagesHandler.postDelayed(movieImagesRunnable, 3000)
    }

    private val movieImagesRunnable: Runnable = Runnable {
        binding.viewpagerMovieImages.currentItem = binding.viewpagerMovieImages.currentItem + 1
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