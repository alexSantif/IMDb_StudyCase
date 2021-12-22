package br.com.alex.imdbstudycase.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import br.com.alex.imdbstudycase.home.R
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        binding.recyclerviewBestMovies.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.HORIZONTAL))
            addItemDecoration(DividerItemDecoration(requireContext(),
                DividerItemDecoration.VERTICAL))
        }

        binding.recyclerviewBestMovies.setItemViewType { _, _ ->
            return@setItemViewType R.layout.shimmer_placeholder_layout
        }
        binding.recyclerviewBestMovies.showShimmer()

        homeViewModel.getMovies()
        homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
            movies?.let { items ->
                homeMoviesAdapter =
                    HomeMoviesAdapter(requireActivity(), items, featureRouter)
                binding.recyclerviewBestMovies.adapter = homeMoviesAdapter
                binding.recyclerviewBestMovies.hideShimmer()
            }
        })

        binding.buttonFavorites.setOnClickListener {
//            featureRouter.start(requireActivity(), OpenFavoritesAction)
        }

        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                binding.searchViewHome.clearFocus()
                binding.recyclerviewBestMovies.showShimmer()
                homeViewModel.getSearchMovie(text)
                homeViewModel.searchMovie.observe(viewLifecycleOwner, { movie ->
                    movie?.let { movies ->
                        homeMoviesAdapter =
                            HomeMoviesAdapter(requireActivity(), movies, featureRouter)
                        binding.recyclerviewBestMovies.adapter = homeMoviesAdapter
                        binding.recyclerviewBestMovies.hideShimmer()
                    }
                })
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        binding.searchViewHome.setOnClickListener {
            binding.searchViewHome.isIconified = false
        }
    }

    companion object {

        const val MOVIE_ID_KEY = "movie_id"
        const val MOVIE_IMAGE_KEY = "movie_image"
    }
}