package com.islam.music.features.main_screen.presentation.view

/*

class AlbumsAdapter(private val onItemClickListener: OnItemClickListener) :
    BaseListAdapter<Album>(AlbumsDiffUtil()) {

    override fun createBinding(parent: ViewGroup, viewType: Int): ViewBinding {
        return OneGeneralItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bind(viewBinding: ViewBinding, item: Album, itemView: View) {
        val binding = viewBinding as OneGeneralItemBinding
        binding.title.text = item.albumName
        loadImage(itemView.context, item.images[0].url, binding)

        itemView.setOnClickListener {
            onItemClickListener.onClick(
                albumName = item.albumName,
                artistName = item.artist.name,
                imgUrl = item.images[0].url
            )
        }
    }

    private fun loadImage(context: Context, url: String, binding: OneGeneralItemBinding) {
        Glide.with(context).load(url)
            .placeholder(R.drawable.ic_placeholder)
            .transition(DrawableTransitionOptions.withCrossFade())
            .thumbnail(IMAGE_SIZE_MULTIPLIER)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.itemImage)
    }

}*/
