package com.islam.music.features.search.presentation.view

/*

class ArtistsAdapter(private val onSearchItemClickListener: OnSearchItemClickListener) :
    BaseListAdapter<Artist>(ArtistDiffUtil()) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return OneGeneralItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(viewBinding: ViewBinding, item: Artist, itemView: View) {
        val binding = viewBinding as OneGeneralItemBinding
        binding.title.text = item.name
        loadImage(itemView.context, item.images?.get(0)?.url, binding)

        itemView.setOnClickListener {
            onSearchItemClickListener.onClick(artistName = item.name)
        }
    }

    private fun loadImage(context: Context, url: String?, binding: OneGeneralItemBinding) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.itemImage)
    }

}*/
