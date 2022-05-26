package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sacharollin.demo_leboncoin.NBRStatus
import com.sacharollin.demo_leboncoin.album.adapter.TracksListAdapter
import com.sacharollin.demo_leboncoin.album.viewmodels.AlbumListViewModel
import com.sacharollin.demo_leboncoin.databinding.FragmentAlbumListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment: Fragment() {

    private val viewModel: AlbumListViewModel by viewModels()
    private val adapter: TracksListAdapter = TracksListAdapter();

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAlbumListBinding.inflate(inflater, container, false)

        handleUi(binding, adapter);

        return binding.root
    }

    private fun handleUi(binding: FragmentAlbumListBinding, adapter: TracksListAdapter) {
        binding.listTracksRecycler.adapter = adapter;

        viewModel.tracks.observe(viewLifecycleOwner) { listTracks ->
            // When success refresh the list with the tracks
            if (listTracks.status == NBRStatus.SUCCESS) {
                adapter.submitList(listTracks.data);
            }
        }
    }
}