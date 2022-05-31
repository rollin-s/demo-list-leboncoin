package com.sacharollin.demo_leboncoin.album.viewmodels

import androidx.lifecycle.*
import com.sacharollin.demo_leboncoin.Resource
import com.sacharollin.demo_leboncoin.album.data.Album
import com.sacharollin.demo_leboncoin.album.data.AlbumRepository
import com.sacharollin.demo_leboncoin.album.data.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject internal constructor(
    albumRepository: AlbumRepository,
): ViewModel() {

    var albumId: MutableLiveData<Int> = MutableLiveData();

    // Get all tracks of the list
    var tracks: LiveData<List<Track>> = Transformations.switchMap(albumId) { albumId ->
        albumRepository.getTracksFromAlbumId(albumId).asLiveData()
    }
}