package com.sacharollin.demo_leboncoin.di

import com.sacharollin.demo_leboncoin.album.api.AlbumService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideAlbumService(): AlbumService {
        return AlbumService.create()
    }
}