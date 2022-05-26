package com.sacharollin.demo_leboncoin.album.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.sacharollin.demo_leboncoin.Resource
import com.sacharollin.demo_leboncoin.album.data.AlbumRepository
import com.sacharollin.demo_leboncoin.album.data.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject internal constructor(
    albumRepository: AlbumRepository,
): ViewModel() {
    // Get all tracks of the list
    var tracks: LiveData<Resource<List<Track>>> = albumRepository.getTracks().asLiveData();
}