package com.islam.music.features.search.presentation.view

/*
///
@AndroidEntryPoint
class SearchFragment :
    BaseFragment<FragmentMainScreenBinding, SearchStates, SearchActions>(),
    SearchView.OnQueryTextListener,
    OnSearchItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun screenTitle() = getString(R.string.search_screen_title)

    override val viewModel: SearchViewModel by viewModels()
    private lateinit var artistsAdapter: ArtistsAdapter
    private var queryTyped = ""
    private var isReachBottom = false

    override fun setupOnViewCreated() {
        initRecyclerView()
        setScrollListener()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            artistsAdapter = ArtistsAdapter(this@SearchFragment)
            adapter = artistsAdapter
        }
    }

    private fun loadArtistList(isLoadMore: Boolean = false) {
        EspressoIdlingResource.increment()
        viewModel.dispatch(
            SearchActions.SearchArtistByName(
                query = queryTyped,
                isLoadMore = isLoadMore
            )
        )
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    override fun handleViewState(it: SearchStates) {
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

}*/
