package com.sacharollin.demo_leboncoin.album.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sacharollin.demo_leboncoin.R
import androidx.databinding.DataBindingUtil.setContentView
import com.sacharollin.demo_leboncoin.databinding.ActivityAlbumBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView<ActivityAlbumBinding>(this, R.layout.activity_album)
    }
}