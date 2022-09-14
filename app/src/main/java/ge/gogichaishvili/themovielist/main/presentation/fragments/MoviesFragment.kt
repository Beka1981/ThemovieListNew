package ge.gogichaishvili.themovielist.main.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ge.gogichaishvili.themovielist.app.network.Resource
import ge.gogichaishvili.themovielist.databinding.FragmentMoviesBinding
import ge.gogichaishvili.themovielist.main.presentation.adapters.ItemsAdapter
import ge.gogichaishvili.themovielist.main.presentation.viewmodels.MoviesViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MoviesViewModel>()

    private lateinit var itemsAdapter: ItemsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()

        lifecycleScope.launch {
            viewModel.moviesFlow.collect {
                when (it) {

                    is Resource.Loading -> {
                    }

                    is Resource.Error -> {
                        Toast.makeText(
                            requireContext(),
                            it.message.toString(),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    is Resource.Success -> {
                        itemsAdapter.updateList(it.data!!.results)
                    }
                }
            }
        }

    }

    private fun setUpRecyclerView() {
        itemsAdapter = ItemsAdapter(
            mutableListOf()
        ).apply {
            setOnItemCLickListener { itemList, i ->
            }
        }
        binding.rvMovies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvMovies.adapter = itemsAdapter
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}