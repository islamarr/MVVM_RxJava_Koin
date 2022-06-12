package com.islam.music.features.top_albums.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsActions
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsStates
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopAlbumsFragment :
    BaseFragment<FragmentMainScreenBinding, TopAlbumsStates, TopAlbumsActions>(),
    OnItemClickListener {

    private val args: TopAlbumsFragmentArgs by navArgs()
    private lateinit var albumsAdapter: AlbumsAdapter

    override val viewModel: TopAlbumsViewModel by viewModels()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun screenTitle() = getString(R.string.top_albums_screen_title, args.artistName)

    override fun setupOnViewCreated() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@TopAlbumsFragment)
            adapter = albumsAdapter
        }
    }

    private fun loadTopAlbums(artistName: String) {
        viewModel.dispatch(TopAlbumsActions.LoadAllAlbums(artistName = artistName))
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    override fun handleViewState(it: TopAlbumsStates) {
        when (it) {
            is TopAlbumsStates.InitialState -> loadTopAlbums(args.artistName)
            is TopAlbumsStates.Loading -> binding.container.loading.visible()
            is TopAlbumsStates.TopAlbumsListLoaded -> {
                showEmptyList(false)
                albumsAdapter.submitList(it.topAlbumsList)
            }
            is TopAlbumsStates.EmptyTopAlbumsList -> {
                showEmptyList(true)
                // binding.retryBtn.gone()
                binding.container.resultStatusText.text = getString(R.string.no_albums)
            }
            is TopAlbumsStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
        }
    }

    override fun onClick(albumName: String?, artistName: String?, imgUrl: String?) {
        albumName?.let {
            findNavController()
                .navigate(
                    TopAlbumsFragmentDirections.actionTopAlbumsFragmentToAlbumDetailsFragment(
                        args.artistName,
                        it,
                        imgUrl
                    )
                )
        }
    }

}