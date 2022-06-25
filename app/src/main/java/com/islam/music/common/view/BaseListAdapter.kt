package com.islam.music.common.view

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding


abstract class BaseListAdapter<T>(callback: DiffUtil.ItemCallback<T> = BaseDiffCallback()) :
    ListAdapter<T, BaseViewHolder<ViewBinding>>(callback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BaseViewHolder(createBinding(parent, viewType))

    override fun onBindViewHolder(holder: BaseViewHolder<ViewBinding>, position: Int) {
        val item = getItem(position)
        bind(holder.binding, item, holder.itemView)
    }

    abstract fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding

    protected abstract fun bind(viewBinding: ViewBinding, item: T, itemView: View)
}

open class BaseDiffCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T) = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: T, newItem: T) = oldItem == newItem
}

open class BaseViewHolder<out T : ViewBinding>(val binding: T) :
    RecyclerView.ViewHolder(binding.root)