package br.com.alex.imdbstudycase.home.presentation

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import br.com.alex.imdbstudycase.home.R
import br.com.alex.imdbstudycase.home.databinding.FragmentHomeBinding
import br.com.alex.imdbstudycase.home.presentation.adapter.BestMoviesAdapter
import br.com.alex.imdbstudycase.router.FeatureRouter
import br.com.alex.imdbstudycase.router.actions.OpenFavoritesAction
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.todkars.shimmer.ShimmerRecyclerView

class HomeFragment : Fragment() {

    private val featureRouter: FeatureRouter by inject()

    private val homeViewModel: HomeViewModel by viewModel()

    private lateinit var binding: FragmentHomeBinding

    private lateinit var bestMoviesAdapter: BestMoviesAdapter

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
        }

        binding.recyclerviewBestMovies.setItemViewType { _, _ ->
            return@setItemViewType R.layout.shimmer_placeholder_layout
        }
        binding.recyclerviewBestMovies.showShimmer()

        homeViewModel.getMovies()
        homeViewModel.movies.observe(viewLifecycleOwner, { movies ->
            if (movies != null) {
                bestMoviesAdapter =
                    BestMoviesAdapter(requireActivity(), movies.items, featureRouter)
                binding.recyclerviewBestMovies.adapter = bestMoviesAdapter
                binding.recyclerviewBestMovies.hideShimmer()
            }
        })

        binding.buttonFavorites.setOnClickListener {
            featureRouter.start(requireActivity(), OpenFavoritesAction)
        }

        binding.searchViewHome.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        binding.searchViewHome.setOnClickListener {
            binding.searchViewHome.setIconified(false)
        }
    }

    companion object {

        fun newInstance() = HomeFragment()
    }
}