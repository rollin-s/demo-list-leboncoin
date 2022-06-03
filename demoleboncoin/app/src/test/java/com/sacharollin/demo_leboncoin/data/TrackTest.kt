package com.sacharollin.demo_leboncoin.data

import com.sacharollin.demo_leboncoin.album.data.Track
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class TrackTest {

    private lateinit var track: Track

    @Before
    fun setUp() {
        track = Track(1, albumId = 1, title = "Nouvelle chanson superbe !", url = "", thumbnailUrl = "")
    }

    @Test
    fun test_ToString_value() {
        Assert.assertTrue(track.toString() == "Nouvelle chanson superbe !")
    }

}