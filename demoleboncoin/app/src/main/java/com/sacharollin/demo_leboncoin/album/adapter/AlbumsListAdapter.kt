package com.sacharollin.demo_leboncoin.album.adapter

import androidx.recyclerview.widget.DiffUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sacharollin.demo_leboncoin.R
import com.sacharollin.demo_leboncoin.album.data.Album
import com.sacharollin.demo_leboncoin.album.ui.AlbumListFragmentDirections
import com.sacharollin.demo_leboncoin.databinding.ListItemAlbumBinding
import com.squareup.picasso.Picasso


class AlbumListAdapter : RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>(
) {
    private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Album>() {
        override fun areItemsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Album, newItem: Album): Boolean {
            return oldItem == newItem

        }
    }
    private var _differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(
            ListItemAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = getItem(position)
        holder.bind(album)
    }

    override fun getItemCount(): Int = _differ.currentList.size

    fun getItem(position: Int): Album = _differ.currentList[position]

    fun updateList(newList: List<Album>) {
        _differ.submitList(newList)
    }

    class AlbumViewHolder(
        private val binding: ListItemAlbumBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) {
            binding.apply {
                album = item
            }

            binding.albumCard.setOnClickListener { view ->
                var action =
                    AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(item.id)
                view.findNavController().navigate(action)
            }

            Picasso
                .get()
                .load(item.thumbnailUrl)
                .into(binding.albumThumbnail)
        }
    }


}
