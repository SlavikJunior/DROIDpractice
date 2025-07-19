package com.example.droidpractice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.droidpractice.databinding.ItemBinding

class Adapter(
    private val onItemClick: (ListItem) -> Unit,
    private val onImageClick: (Int) -> Unit
) : RecyclerView.Adapter<Adapter.Holder>() {

    val picturesList = listOf(
        "https://i.pinimg.com/736x/14/91/fc/1491fc0628b9845dfcddd24699cbe71d.jpg",
        "https://i.pinimg.com/736x/70/c3/e9/70c3e9e65461e745c627a9fee67cc513.jpg",
        "https://i.pinimg.com/736x/f1/36/38/f136386414fc23a89b0eb35d1ab25a2a.jpg",
        "https://i.pinimg.com/736x/23/17/f0/2317f0ecef5f693a51332c99323cdad6.jpg",
        "https://i.pinimg.com/736x/24/41/82/244182673f486ba9246b192fb516cfee.jpg",
        "https://i.pinimg.com/736x/f7/0e/88/f70e885082bebaad32634247f7a02d33.jpg",
        "https://i.pinimg.com/736x/a7/4c/4c/a74c4c738bb8d3afdbc70814af483c04.jpg",
        "https://i.pinimg.com/736x/af/bf/a3/afbfa3a32c21f1176abf6b74eb50db46.jpg",
        "https://i.pinimg.com/736x/02/59/69/0259699a168aea21ba838cd4873a1fdc.jpg",
        "https://i.pinimg.com/736x/8f/1e/22/8f1e223135a6a34674597a6fec07bd70.jpg",
        "https://i.pinimg.com/736x/50/cf/ac/50cfacdc2484cebe435d44f356257087.jpg"
    )

    val items = mutableListOf<ListItem>()

    inner class Holder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ListItem, position: Int) = with(binding) {
            Glide.with(itemView)
                .load(item.imageUrl)
                .transition(DrawableTransitionOptions.withCrossFade(300))
                .into(img)

            tvTitle.text = item.title
            tvDesc.text = item.description

            img.setOnClickListener { onImageClick(position) }
            root.setOnClickListener { onItemClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return Holder(binding)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position], position)
    }

    override fun getItemCount() = items.size

    fun submitList(newItems: List<ListItem>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}