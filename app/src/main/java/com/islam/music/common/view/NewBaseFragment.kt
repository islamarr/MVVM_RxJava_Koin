package com.islam.music.common.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewbinding.ViewBinding
import com.islam.music.common.Action
import com.islam.music.common.ViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

abstract class NewBaseFragment<viewBinding : ViewBinding> :
    Fragment() {

    private var _binding: viewBinding? = null
    protected val binding: viewBinding
        get() {
            return _binding ?: throw IllegalStateException(
                "data binding should not be requested before onViewCreated is called"
            )
        }

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> viewBinding

    abstract fun screenTitle(): String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(layoutInflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        setToolbarTitle()
        setupOnViewCreated()

    }

    private fun setToolbarTitle() {
        (requireActivity() as AppCompatActivity).supportActionBar?.title = screenTitle()
    }

    abstract fun setupOnViewCreated()

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}