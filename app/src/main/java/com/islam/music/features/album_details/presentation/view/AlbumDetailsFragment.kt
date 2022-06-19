package com.islam.music.features.album_details.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.islam.music.R
import com.islam.music.common.IMAGE_SIZE_MULTIPLIER
import com.islam.music.common.data.DataResponse
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentAlbumDetailsBinding
import com.islam.music.features.album_details.domain.entites.AlbumEntity
import com.islam.music.features.album_details.domain.entites.AlbumParams
import com.islam.music.features.album_details.presentation.viewmodel.AlbumDetailsViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AlbumDetailsFragment :
    BaseFragment<FragmentAlbumDetailsBinding>() {

    private val args: AlbumDetailsFragmentArgs by navArgs()
    private var albumEntity = AlbumEntity()
    private var isFavorite: Boolean = false
    private val trackAdapter: TrackAdapter by inject()
    private lateinit var albumParams: AlbumParams

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAlbumDetailsBinding
        get() = FragmentAlbumDetailsBinding::inflate

    override fun screenTitle() = getString(R.string.album_details_screen_title, args.albumName)
    val viewModel: AlbumDetailsViewModel by viewModel()


    override fun setupOnViewCreated() {
        initRecyclerView()
        setArgumentsData()
        loadAlbumDetails(albumParams)
        handleViewState()
        binding.addToFavorite.setOnClickListener {
            isFavorite = !isFavorite
            viewModel.setFavorite(isFavorite, albumEntity)
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
        viewModel.fetch(albumParams)
    }

    private fun showEmptyList(show: Boolean) {
        binding.loading.gone()
        binding.resultStatusText.isVisible = show
        binding.albumTrackList.isVisible = !show
    }

    private fun handleViewState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataResponse.Loading -> binding.loading.visible()
                is DataResponse.Failure -> {
                    showEmptyList(true)
                    binding.resultStatusText.text = getString(R.string.no_tracks)
                }
                is DataResponse.Success -> {
                    it.data?.albumDetails?.let { album ->
                        val resultTrackList = album.trackList
                        binding.addToFavorite.visible()
                        showEmptyList(resultTrackList.isEmpty())
                        trackAdapter.submitList(resultTrackList)
                        albumEntity = album
                    }
                }
            }
        }

        viewModel.state2.observe(viewLifecycleOwner) {
            isFavorite = it.isSaved
            binding.addToFavorite.isChecked = isFavorite
        }
    }

}