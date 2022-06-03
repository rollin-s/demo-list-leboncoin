package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orhanobut.logger.Logger

import com.sacharollin.demo_leboncoin.album.adapter.TracksListAdapter
import com.sacharollin.demo_leboncoin.album.viewmodels.AlbumDetailViewModel
import com.sacharollin.demo_leboncoin.databinding.FragmentDetailAlbumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailFragment : Fragment() {

    private val viewModel: AlbumDetailViewModel by viewModels()

    private lateinit var binding: FragmentDetailAlbumBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailAlbumBinding.inflate(inflater, container, false).apply {
            viewModel = this.viewModel
        }


        val adapter = TracksListAdapter()
        binding.listTracksRecycler.adapter = adapter
        binding.listTracksRecycler.layoutManager = LinearLayoutManager(context);

        handleUi(adapter)

        // When the view is created, we want to force the refresh of the list
        viewModel.albumId.postValue(arguments?.getInt("albumId"))
        (activity as AppCompatActivity).supportActionBar?.title = "Listes des albums"

        return binding.root
    }

    private fun handleUi(adapter: TracksListAdapter) {
        viewModel.tracks.observe(viewLifecycleOwner) { listTracks ->
            // When success refresh the list with the tracks
            Logger.d("Update the list with ${listTracks.size} elements")
            adapter.submitList(listTracks)
        }
    }
}
