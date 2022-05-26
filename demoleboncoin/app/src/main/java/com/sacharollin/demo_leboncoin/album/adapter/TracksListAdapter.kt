package com.sacharollin.demo_leboncoin.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sacharollin.demo_leboncoin.album.data.Track
import com.sacharollin.demo_leboncoin.databinding.ListItemTrackBinding

class TracksListAdapter: ListAdapter<Track, RecyclerView.ViewHolder>(TrackDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TrackViewHolder(
            ListItemTrackBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val track = getItem(position)
        (holder as TrackViewHolder).bind(track)
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

class TrackDiffCallback: DiffUtil.ItemCallback<Track>() {
    override fun areItemsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Track, newItem: Track): Boolean {
        return oldItem == newItem;
    }

}