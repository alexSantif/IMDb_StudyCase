package br.com.alex.imdbstudycase.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.presentation.adapter.BestMoviesAdapter
import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.actions.OpenFavoritesAction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val featureRouter: FeatureRouter by inject()

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var recyclerViewBestMovies: RecyclerView

    private lateinit var buttonFavorites: Button

    private lateinit var bestMoviesAdapter: BestMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)

        homeViewModel.getMovies()
        homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                bestMoviesAdapter = BestMoviesAdapter(requireActivity(), movies.items, featureRouter)

                recyclerViewBestMovies.apply {
                    layoutManager = GridLayoutManager(requireContext(), 2)
                    adapter = bestMoviesAdapter
                }
            }
        })

        buttonFavorites.setOnClickListener {
            featureRouter.start(requireActivity(), OpenFavoritesAction)
        }
    }

    private fun initViews(view: View) {
        recyclerViewBestMovies = view.findViewById(R.id.recyclerview_best_movies)
        buttonFavorites = view.findViewById(R.id.button_favorites)
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}