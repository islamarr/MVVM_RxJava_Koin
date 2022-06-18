package com.islam.music.features.search.presentation.view

import android.app.SearchManager
import android.content.Context.SEARCH_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.islam.music.R
import com.islam.music.common.EspressoIdlingResource
import com.islam.music.common.data.DataResponse
import com.islam.music.common.gone
import com.islam.music.common.setKeyboardVisibility
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.search.presentation.viewmodel.SearchStates
import com.islam.music.features.search.presentation.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment(),
    SearchView.OnQueryTextListener,
    OnSearchItemClickListener {

    private var _binding: FragmentMainScreenBinding? = null
    private val binding: FragmentMainScreenBinding
        get() {
            return _binding ?: throw IllegalStateException(
                "data binding should not be requested before onViewCreated is called"
            )
        }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainScreenBinding.inflate(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setToolbarTitle()
        initRecyclerView()
        setScrollListener()
        startObserver()
    }

    private fun setToolbarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = screenTitle()
    }

    fun screenTitle() = getString(R.string.search_screen_title)

    private fun startObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataResponse.Failure -> {
                    showEmptyList(true)
                    binding.container.resultStatusText.text = getString(R.string.error_message)
                    EspressoIdlingResource.decrement()
                }
               // is DataResponse.Loading -> binding.container.loading.visible()
                is DataResponse.Success -> {
                    showEmptyList(false)
                    it.data?.let { state ->
                        isReachBottom = state.isReachBottom
                    }
                    it.data?.result?.let { list ->
                        if (list.isEmpty()) {
                            showEmptyList(true)
                            binding.container.resultStatusText.text = getString(R.string.no_Artists)
                        } else {
                            artistsAdapter.submitList(list.toList())
                        }
                    }
                    EspressoIdlingResource.decrement()
                }
            }
        }
    }

    val viewModel: SearchViewModel by viewModels()
    private lateinit var artistsAdapter: ArtistsAdapter
    private var queryTyped = ""
    private var isReachBottom = false


    private fun initRecyclerView() {
        binding.container.list.apply {
            artistsAdapter = ArtistsAdapter(this@SearchFragment)
            adapter = artistsAdapter
        }
    }

    private fun loadArtistList(isLoadMore: Boolean = false) {
        EspressoIdlingResource.increment()
        viewModel.fetchArtists(
            query = queryTyped,
            isLoadMore = isLoadMore
        )
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    fun handleViewState(it: SearchStates) {
        when (it) {
            is SearchStates.InitialState -> Log.d("TAG", "InitialState")
            is SearchStates.Loading -> binding.container.loading.visible()
            is SearchStates.ArtistListLoaded -> {
                showEmptyList(false)
                isReachBottom = it.isReachBottom
                artistsAdapter.submitList(it.result.toList())
                EspressoIdlingResource.decrement()
            }
            is SearchStates.EmptyArtistList -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.no_Artists)
                EspressoIdlingResource.decrement()
            }
            is SearchStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
                EspressoIdlingResource.decrement()
            }
        }
    }

    private fun setScrollListener() {
        binding.container.list.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (!recyclerView.canScrollVertically(1) && !binding.container.loading.isVisible && !isReachBottom) {
                    loadArtistList(isLoadMore = true)
                }
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
        val searchManager = requireActivity().getSystemService(SEARCH_SERVICE) as SearchManager
        val searchView: SearchView = menu.findItem(R.id.startSearch)?.actionView as SearchView
        setSearchView(searchView, searchManager)
    }

    private fun setSearchView(
        searchView: SearchView,
        searchManager: SearchManager
    ) {
        searchView.apply {
            queryHint = resources.getString(R.string.search_hint)
            setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
            setIconifiedByDefault(false)
            isSubmitButtonEnabled = true
            isIconified = false
            setOnQueryTextListener(this@SearchFragment)
        }

    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        setKeyboardVisibility(isShow = false)
        query?.let {
            queryTyped = it
            loadArtistList()
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    override fun onClick(artistName: String?) {
        setKeyboardVisibility(isShow = false)
        artistName?.let {
            findNavController()
                .navigate(SearchFragmentDirections.actionSearchFragmentToTopAlbumsFragment(it))
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}