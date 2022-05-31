package com.sacharollin.demo_leboncoin.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.sacharollin.demo_leboncoin.R
import com.sacharollin.demo_leboncoin.album.data.Album
import com.sacharollin.demo_leboncoin.album.ui.AlbumDetailFragmentArgs
import com.sacharollin.demo_leboncoin.album.ui.AlbumListFragmentDirections
import com.sacharollin.demo_leboncoin.databinding.ListItemAlbumBinding
import retrofit2.Response.error

class AlbumListAdapter: RecyclerView.Adapter<AlbumListAdapter.AlbumViewHolder>(
) {
    var tracks: List<Album> = listOf()

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

    override fun getItemCount(): Int = tracks.size

    fun getItem(position: Int): Album = tracks[position]

    fun updateList(newList: List<Album>?) {
        this.tracks = newList ?: listOf()
        this.notifyItemInserted(0)
    }

    class AlbumViewHolder(
        private val binding: ListItemAlbumBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Album) {
            binding.apply {
                album = item
            }

            binding.albumCard.setOnClickListener { view ->
                var action = AlbumListFragmentDirections.actionAlbumListFragmentToAlbumDetailFragment(item.id)
                view.findNavController().navigate(action)
            }

            Glide
                .with(itemView)
                .load(item.thumbnailUrl)
                .centerCrop()
                .error(R.drawable.image_error)
                .into(binding.albumThumbnail)
        }
    }
}
