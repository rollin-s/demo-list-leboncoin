package com.sacharollin.demo_leboncoin.album.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sacharollin.demo_leboncoin.album.data.Track
import com.sacharollin.demo_leboncoin.databinding.ListItemTrackBinding
import okhttp3.internal.notifyAll

class TracksListAdapter: RecyclerView.Adapter<TracksListAdapter.TrackViewHolder>(
) {
    var tracks: List<Track> = listOf()

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
        val track = getItem(position)
        holder.bind(track)    }

    override fun getItemCount(): Int = tracks.size

    fun getItem(position: Int): Track = tracks[position]

    fun updateList(newList: List<Track>?) {
        this.tracks = newList ?: listOf()
        this.notifyItemInserted(0)
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
