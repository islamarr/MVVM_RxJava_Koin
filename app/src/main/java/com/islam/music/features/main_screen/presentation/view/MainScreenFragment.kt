package com.islam.music.features.main_screen.presentation.view

import android.util.Log
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenActions
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenStates
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment :
    BaseFragment<FragmentMainScreenBinding, MainScreenStates, MainScreenActions>(),
    OnItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate
    override fun screenTitle() = getString(R.string.main_screen_title)

    override val viewModel: MainScreenViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated() {
        initRecyclerView()
        loadAlbumList() //TODO you should use flow to refresh the list not update it manually
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@MainScreenFragment)
            adapter = albumsAdapter
        }
    }

    private fun loadAlbumList() {
        viewModel.dispatch(MainScreenActions.LoadAllSavedAlbums)
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
    }

    override fun handleViewState(it: MainScreenStates) {
        when (it) {
            is MainScreenStates.InitialState -> Log.d("TAG", "InitialState")
            is MainScreenStates.Loading -> binding.container.loading.visible()
            is MainScreenStates.SavedListLoaded -> {
                showEmptyList(false)
                albumsAdapter.submitList(it.topAlbumsList)
            }
            is MainScreenStates.EmptySavedList -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.no_favorite)
            }
            is MainScreenStates.ShowErrorMessage -> {
                showEmptyList(true)
                binding.container.resultStatusText.text = getString(R.string.error_message)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.startSearch -> {
                findNavController().navigate(R.id.action_mainScreenFragment_to_searchFragment)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onClick(albumName: String?, artistName: String?, imgUrl: String?) {
        if (albumName != null && artistName != null) {
            findNavController()
                .navigate(
                    MainScreenFragmentDirections.actionMainScreenFragmentToAlbumDetailsFragment(
                        artistName,
                        albumName,
                        imgUrl
                    )
                )
        }
    }

}