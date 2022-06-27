package com.islam.music.common

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.SearchView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun Fragment.setKeyboardVisibility(isShow: Boolean = true) {
    view?.let { activity?.setKeyboardVisibility(it, isShow) }
}

fun Context.setKeyboardVisibility(view: View, isShow: Boolean) {
    val inputMethodManager =
        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (isShow)
        inputMethodManager.showSoftInput(
            view,
            InputMethodManager.SHOW_FORCED
        )
    else
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun androidx.appcompat.widget.SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {
    val query = MutableStateFlow("")

    setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query
}