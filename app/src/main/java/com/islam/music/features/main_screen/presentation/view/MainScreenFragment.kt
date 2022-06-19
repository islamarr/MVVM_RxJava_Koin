package com.islam.music.features.main_screen.presentation.view

import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.islam.music.R
import com.islam.music.common.EspressoIdlingResource
import com.islam.music.common.OnItemClickListener
import com.islam.music.common.data.DataResponse
import com.islam.music.common.gone
import com.islam.music.common.view.BaseFragment
import com.islam.music.common.visible
import com.islam.music.databinding.FragmentMainScreenBinding
import com.islam.music.features.main_screen.presentation.viewmodel.MainScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenFragment :
    BaseFragment<FragmentMainScreenBinding>(),
    OnItemClickListener {

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMainScreenBinding
        get() = FragmentMainScreenBinding::inflate

    override fun screenTitle() = getString(R.string.main_screen_title)

    val viewModel: MainScreenViewModel by viewModels()
    private lateinit var albumsAdapter: AlbumsAdapter

    override fun setupOnViewCreated() {
        initRecyclerView()
        startObserver()
    }

    private fun startObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            when (it) {
                is DataResponse.Failure -> {
                    showEmptyList(true)
                    binding.container.resultStatusText.text = getString(R.string.error_message)
                    EspressoIdlingResource.decrement()
                }
                is DataResponse.Loading -> binding.container.loading.visible()
                is DataResponse.Success -> {
                    showEmptyList(false)
                    it.data?.topAlbumsList?.let { list ->
                        if (list.isEmpty()) {
                            showEmptyList(true)
                            binding.container.resultStatusText.text =
                                getString(R.string.no_favorite)
                        } else {
                            showEmptyList(false)
                            albumsAdapter.submitList(list)
                        }
                    }
                    EspressoIdlingResource.decrement()
                }
            }
        }
    }

    private fun initRecyclerView() {
        binding.container.list.apply {
            albumsAdapter = AlbumsAdapter(this@MainScreenFragment)
            adapter = albumsAdapter
        }
    }

    private fun showEmptyList(show: Boolean) {
        binding.container.loading.gone()
        binding.container.resultStatusText.isVisible = show
        binding.container.list.isVisible = !show
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