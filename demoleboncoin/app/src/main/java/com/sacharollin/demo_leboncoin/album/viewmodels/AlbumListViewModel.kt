package com.sacharollin.demo_leboncoin.album.viewmodels

import androidx.lifecycle.*
import com.sacharollin.demo_leboncoin.Resource
import com.sacharollin.demo_leboncoin.album.data.Album
import com.sacharollin.demo_leboncoin.album.data.AlbumRepository
import com.sacharollin.demo_leboncoin.album.data.Track
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AlbumListViewModel @Inject internal constructor(
    albumRepository: AlbumRepository,
): ViewModel() {

    var shouldRefresh: MutableLiveData<Boolean> = MutableLiveData()

    // Get all tracks of the list
    var tracks: LiveData<Resource<List<Track>>> = Transformations.switchMap(shouldRefresh) { shouldRefresh ->
        albumRepository.getTracks(shouldRefresh).asLiveData()
    }

    // Get all albums of the list
    var albums: LiveData<Resource<List<Album>>> = Transformations.switchMap(shouldRefresh) { shouldRefresh ->
        albumRepository.getAlbums(shouldRefresh).asLiveData()
    }


    fun refresh(shouldRefresh: Boolean) {
        this.shouldRefresh.postValue(shouldRefresh);
    }
}