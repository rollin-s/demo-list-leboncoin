package com.sacharollin.demo_leboncoin.album.api

import com.orhanobut.logger.Logger
import com.sacharollin.demo_leboncoin.album.data.Track
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


interface AlbumService {
    @GET("img/shared/technical-test.json")
    suspend fun getTracks(): List<Track>

    companion object {
        private const val API_URL = "https://static.leboncoin.fr"

        fun create(): AlbumService {
            val logger = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            }

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

            return Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(AlbumService::class.java)
        }
    }
}