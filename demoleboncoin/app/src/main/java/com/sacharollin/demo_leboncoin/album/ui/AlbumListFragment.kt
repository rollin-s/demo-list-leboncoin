package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.sacharollin.demo_leboncoin.album.viewmodels.AlbumListViewModel
import com.sacharollin.demo_leboncoin.databinding.FragmentAlbumListBinding
import kotlinx.coroutines.flow.collect

class AlbumListFragment: Fragment() {

    private val viewModel: AlbumListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAlbumListBinding.inflate(inflater, container, false)

        handleUi();

        return binding.root
    }

    private suspend fun handleUi() {
        viewModel.tracks.collect { resourceList ->

        }
    }
}