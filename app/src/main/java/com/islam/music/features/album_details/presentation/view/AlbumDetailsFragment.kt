package com.islam.music.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.islam.music.R
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentAlbumDetailsBinding
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsActions
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsStates
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

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

}