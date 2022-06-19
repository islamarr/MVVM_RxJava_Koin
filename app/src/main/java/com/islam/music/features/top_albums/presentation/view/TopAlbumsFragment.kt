package com.islam.music.features.top_albums.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.data.DataResponse
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.main_screen.presentation.view.AlbumsAdapter
import com.islam.music.features.top_albums.presentation.viewmodel.TopAlbumsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class TopAlbumsFragment :
    BaseFragment<FragmentMainScreenBinding>(),
    OnItemClickListener {

    private val args: TopAlbumsFragmentArgs by navArgs()
    private lateinit var albumsAdapter: AlbumsAdapter

    val viewModel: TopAlbumsViewModel by viewModel()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun screenTitle() = getString(R.string.top_albums_screen_title, args.artistName)

    override fun setupOnViewCreated() {
        initRecyclerView()
        loadTopAlbums(args.artistName)
        handleViewState()
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@TopAlbumsFragment)
            adapter = albumsAdapter
        }
    }

    private fun loadTopAlbums(artistName: String) {
        viewModel.fetch(artistName = artistName)
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    private fun handleViewState() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataResponse.Loading -> binding.container.loading.visible()
                is DataResponse.Failure -> {
                    showEmptyList(true)
                    binding.container.resultStatusText.text = getString(R.string.error_message)
                }
                is DataResponse.Success -> {
                    it.data?.topAlbumsList?.let { list ->
                        if (list.isEmpty()) {
                            showEmptyList(true)
                            // binding.retryBtn.gone()
                            binding.container.resultStatusText.text = getString(R.string.no_albums)
                        } else {
                            showEmptyList(false)
                            albumsAdapter.submitList(list)
                        }
                    }
                }
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