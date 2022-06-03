package com.sacharollin.demo_leboncoin.album.data

import androidx.paging.DataSource
import com.orhanobut.logger.Logger
import com.sacharollin.demo_leboncoin.Resource
import com.sacharollin.demo_leboncoin.album.api.AlbumService
import com.sacharollin.demo_leboncoin.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject internal constructor(
    private val albumDao: AlbumDao,
    private val albumService: AlbumService,
) {

    fun getTracks(shouldRefresh: Boolean): Flow<Resource<List<Track>>> {
        return networkBoundResource(
            query = { albumDao.getTracks() },
            fetch = {
                Logger.d("API CALL IS MADE")
                albumService.getTracks()
            },
            saveFetchResult = { response ->
                albumDao.insertTracks(response)
            },
            shouldFetch = { shouldRefresh } // Might want to change this to prevent fetching all the datas everytime
        )
    }


    fun getTracksFromAlbumId(albumId: Int): DataSource.Factory<Int, Track> {
        return albumDao.getTracksFromAlbumId(albumId)
    }


    fun getAlbums(shouldRefresh: Boolean): Flow<Resource<List<Album>>> {
        return networkBoundResource(
            query = { albumDao.getAlbums() },
            fetch = {
                albumService.getTracks()
            },
            saveFetchResult = { response ->
                albumDao.insertTracks(response)
            },
            shouldFetch = { shouldRefresh } // Might want to change this to prevent fetching all the datas everytime
        )
    }

}