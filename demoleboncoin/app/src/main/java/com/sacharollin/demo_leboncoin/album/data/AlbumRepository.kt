package com.sacharollin.demo_leboncoin.album.data

import com.sacharollin.demo_leboncoin.Resource
import com.sacharollin.demo_leboncoin.album.api.AlbumService
import com.sacharollin.demo_leboncoin.networkBoundResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlbumRepository @Inject internal constructor(
    private val albumDao: AlbumDao,
    private val albumService: AlbumService,
) {

    fun getTracks(): Flow<Resource<List<Track>>> {
        return networkBoundResource(
            query = { albumDao.getTracks() },
            fetch = { albumService.getTracks() },
            saveFetchResult = { response -> albumDao.insertTracks(response) },
            shouldFetch = { true } // Might want to change this to prevent fetching all the datas everytime
        )
    }

}