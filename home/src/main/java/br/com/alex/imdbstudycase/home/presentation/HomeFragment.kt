package br.com.alex.imdbstudycase.home.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Button
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.data.model.MovieModelData
import br.com.alex.imdbstudycase.home.databinding.FragmentHomeBinding
import br.com.alex.imdbstudycase.home.presentation.adapter.HomeMoviesAdapter
import br.com.alex.imdbstudycase.router.FeatureRouter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private val featureRouter: FeatureRouter by inject()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var homeMoviesAdapter: HomeMoviesAdapter

    private lateinit var textviewErrorMessage: TextView
    private lateinit var errorButton: Button

    private var query = "best_movies"
    private var searchMovie = ""

    private var bestMoviesItems: List<MovieModelData> = listOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        errorButton = view.findViewById(R.id.button_error_try_again)
        textviewErrorMessage = view.findViewById(R.id.textview_error_message)

        setHomeMoviesList()
        getBestMovies()
        openFavorites()
        setSearchViewActions()
    }

    private fun setSearchViewActions() {
        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                binding.textviewNoContentMessage.visibility = GONE
                binding.searchViewHome.clearFocus()
                binding.recyclerviewHomeMovies.showShimmer()
                getSearchedMovie(text)
                searchMovie = text.orEmpty()
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                if (text.isNullOrEmpty()) {
                    binding.textviewNoContentMessage.visibility = GONE
                    showHomeMoviesList(bestMoviesItems)
                    homeViewModel.eraseMovieSearch()
                }
                return false
            }
        })

        binding.searchViewHome.setOnClickListener {
            binding.searchViewHome.isIconified = false
        }
    }

    private fun getSearchedMovie(text: String?) {
        if (checkForInternet(requireContext())) {
            homeViewModel.getSearchMovie(text)
            homeViewModel.searchMovie.observe(viewLifecycleOwner, { data ->
                data?.data?.let { movies ->
                    showHomeMoviesList(movies as List<MovieModelData>)
                } ?: let {
                    data?.error?.let { error ->
                        showErrorScreen("Erro ao carregar lista de filmes")
                        query = "search_movies"
                    }
                }
            })
        } else {
            showErrorScreen("Sem conexão com a Internet")
            query = "search_movies"
        }
    }

    private fun getBestMovies() {
        if (checkForInternet(requireContext())) {
            homeViewModel.getMovies()
            homeViewModel.movies.observe(viewLifecycleOwner, { data ->
                data?.data?.let { movies ->
                    bestMoviesItems = movies as List<MovieModelData>
                    showHomeMoviesList(movies)
                } ?: let {
                    data?.error?.let { error ->
                        showErrorScreen("Erro ao carregar lista de filmes")
                        query = "best_movies"
                    }
                }
            })
        } else {
            showErrorScreen("Sem conexão com a Internet")
            query = "best_movies"
        }
    }

    private fun showErrorScreen(errorMessage: String) {
        binding.recyclerviewHomeMovies.hideShimmer()
        binding.searchViewHome.visibility = GONE
        binding.recyclerviewHomeMovies.visibility = GONE
        binding.buttonFavorites.visibility = GONE
        binding.homeErrorView.visibility = VISIBLE

        textviewErrorMessage.text = errorMessage
        errorButton.setOnClickListener {
            hideErrorScreen()
            if (query == "best_movies") {
                getBestMovies()
            } else {
                getSearchedMovie(searchMovie)
            }
        }
    }

    private fun hideErrorScreen() {
        binding.recyclerviewHomeMovies.showShimmer()
        binding.searchViewHome.visibility = VISIBLE
        binding.recyclerviewHomeMovies.visibility = VISIBLE
        binding.buttonFavorites.visibility = VISIBLE
        binding.homeErrorView.visibility = GONE
    }

    private fun showHomeMoviesList(items: List<MovieModelData>) {
        if (items.isEmpty()) {
            showNoResultsMessage()
        } else {
            homeMoviesAdapter =
                HomeMoviesAdapter(requireActivity(), items, featureRouter)
            binding.recyclerviewHomeMovies.adapter = homeMoviesAdapter
            binding.recyclerviewHomeMovies.hideShimmer()
        }
    }

    private fun showNoResultsMessage() {
        binding.recyclerviewHomeMovies.hideShimmer()
        binding.textviewNoContentMessage.visibility = VISIBLE
    }

    private fun setHomeMoviesList() {
        binding.recyclerviewHomeMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.HORIZONTAL
                )
            )
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.recyclerviewHomeMovies.setItemViewType { _, _ ->
            return@setItemViewType R.layout.shimmer_placeholder_layout
        }
        binding.recyclerviewHomeMovies.showShimmer()
    }

    private fun openFavorites() {
        binding.buttonFavorites.setOnClickListener {
            //            featureRouter.start(requireActivity(), OpenFavoritesAction)
        }
    }

    private fun checkForInternet(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }

    companion object {

        const val MOVIE_ID_KEY = "movie_id"
    }
}