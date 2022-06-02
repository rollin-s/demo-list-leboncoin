package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.orhanobut.logger.Logger
import com.sacharollin.demo_leboncoin.NBRStatus
import com.sacharollin.demo_leboncoin.album.adapter.AlbumListAdapter
import com.sacharollin.demo_leboncoin.album.adapter.TracksListAdapter
import com.sacharollin.demo_leboncoin.album.data.Track
import com.sacharollin.demo_leboncoin.album.viewmodels.AlbumListViewModel
import com.sacharollin.demo_leboncoin.databinding.FragmentAlbumListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumListFragment : Fragment() {

    private val viewModel: AlbumListViewModel by viewModels()

    private lateinit var binding: FragmentAlbumListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentAlbumListBinding.inflate(inflater, container, false).apply {
            viewModel = this.viewModel
        }

        (activity as AppCompatActivity).supportActionBar?.title = "Detail d'un album"

        // Force the refresh from the API
        binding.swipeRefreshRecycler.setOnRefreshListener {
            viewModel.refresh(true)
        }

        val adapter = AlbumListAdapter()
        binding.listTracksRecycler.adapter = adapter
        binding.listTracksRecycler.layoutManager = GridLayoutManager(context, 3)

        handleUi(adapter)

        // When the view is created, we want to force the refresh of the list
        viewModel.refresh(true)
        return binding.root
    }

    private fun handleUi(adapter: AlbumListAdapter) {
        viewModel.albums.observe(viewLifecycleOwner) { listAlbums ->
            // When success refresh the list with the tracks
            if (listAlbums.status == NBRStatus.SUCCESS || (listAlbums.data?.isNotEmpty() == true)) {
                adapter.updateList(listAlbums.data ?: listOf())
                binding.swipeRefreshRecycler.isRefreshing = false
            }
        }
    }
}