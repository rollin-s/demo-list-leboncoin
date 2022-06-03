package com.sacharollin.demo_leboncoin.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sacharollin.demo_leboncoin.album.data.Track
import com.sacharollin.demo_leboncoin.databinding.ListItemTrackBinding

class TracksListAdapter: PagedListAdapter<Track, TracksListAdapter.TrackViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Track>() {
            override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
                return oldItem == newItem

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return TrackViewHolder(
            ListItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    class TrackViewHolder(
        private val binding: ListItemTrackBinding,
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Track) {
            binding.apply {
                track = item
            }
        }
    }
}
