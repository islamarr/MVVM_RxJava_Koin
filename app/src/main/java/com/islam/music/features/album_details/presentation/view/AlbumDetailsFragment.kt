package com.islam.music.features.album_details.presentation.view

/*

@AndroidEntryPoint
class AlbumDetailsFragment :
    BaseFragment<FragmentAlbumDetailsBinding, AlbumDetailsStates, AlbumDetailsActions>() {

    private val args: AlbumDetailsFragmentArgs by navArgs()
    private var albumEntity = AlbumEntity()
    private var isFavorite: Boolean = false
    private lateinit var trackAdapter: TrackAdapter
    private lateinit var albumParams: AlbumParams

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumDetailsBinding
        get() = FragmentAlbumDetailsBinding::inflate
    override fun screenTitle() = getString(R.string.album_details_screen_title, args.albumName)
    override val viewModel: AlbumDetailsViewModel by viewModels()


    override fun setupOnViewCreated() {
        initRecyclerView()
        setArgumentsData()
        binding.addToFavorite.setOnClickListener {
            isFavorite = !isFavorite
            viewModel.dispatch(AlbumDetailsActions.SetFavoriteAction(isFavorite, albumEntity))
        }
    }

    private fun setArgumentsData() {
        binding.albumName.text = args.albumName
        binding.albumArtistName.text = args.artistName
        loadImage(args.imageUrl)
        albumParams = AlbumParams(args.artistName, args.albumName)
    }

    private fun initRecyclerView() {
        binding.albumTrackList.apply {
            trackAdapter = TrackAdapter()
            adapter = trackAdapter
        }
    }

    private fun loadImage(url: String?) {
        Glide.with(requireContext()).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.albumCoverImage)
    }

    private fun loadAlbumDetails(albumParams: AlbumParams) {
        viewModel.dispatch(AlbumDetailsActions.AlbumDetailsAction(albumParams))
    }

    private fun showEmptyList(show: Boolean) {
        binding.loading.gone()
        binding.resultStatusText.isVisible = show
        binding.albumTrackList.isVisible = !show
    }

    override fun handleViewState(it: AlbumDetailsStates) {
        when (it) {
            is AlbumDetailsStates.InitialState -> loadAlbumDetails(albumParams)
            is AlbumDetailsStates.Loading -> binding.loading.visible()
            is AlbumDetailsStates.AlbumDetailsData -> {
                val resultTrackList = it.albumDetails.trackList
                binding.addToFavorite.visible()
                showEmptyList(resultTrackList.isEmpty())
                trackAdapter.submitList(resultTrackList)
                albumEntity = it.albumDetails
            }
            is AlbumDetailsStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.resultStatusText.text = getString(R.string.no_tracks)
            }
            is AlbumDetailsStates.SavedState -> {
                isFavorite = it.isSaved
                binding.addToFavorite.isChecked = isFavorite
            }
        }
    }

}*/
