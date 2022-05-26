package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.sacharollin.demo_leboncoin.R
import com.sacharollin.demo_leboncoin.databinding.ActivityAlbumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        setContentView<ActivityAlbumBinding>(this, R.layout.activity_album);
    }
}