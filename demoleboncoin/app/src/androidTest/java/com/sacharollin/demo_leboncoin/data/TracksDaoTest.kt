package com.sacharollin.demo_leboncoin.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.sacharollin.demo_leboncoin.AppDatabase
import com.sacharollin.demo_leboncoin.album.data.AlbumDao
import com.sacharollin.demo_leboncoin.album.data.Track
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TracksDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var tracksDao: AlbumDao
    private val trackA = Track(1, albumId = 1, title = "Showing the sun", url = "", thumbnailUrl = "")
    private val trackB = Track(2, albumId = 1, title = "Telling the truth", url = "", thumbnailUrl = "")
    private val trackC = Track(3, albumId = 1, title = "Black Hole Sun", url = "", thumbnailUrl = "")
    private val trackD = Track(4, albumId = 2, title = "Moderat", url = "", thumbnailUrl = "")
    private val trackE = Track(5, albumId = 2, title = "MORE LOVE", url = "", thumbnailUrl = "")
    private val trackF = Track(6, albumId = 2, title = "Blanka", url = "", thumbnailUrl = "")
    private val trackG = Track(7, albumId = 2, title = "L'odeur de l'essence", url = "", thumbnailUrl = "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        tracksDao = database.albumDao()

        // Insert plants in non-alphabetical order to test that results are sorted by name
        tracksDao.insertTracks(listOf(trackA, trackB, trackC, trackD, trackE, trackF))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetAlbums() = runBlocking {
        val plantList = tracksDao.getAlbums().first()
        MatcherAssert.assertThat(plantList.size, Matchers.equalTo(2))

        // Ensure plant list is sorted by name
        MatcherAssert.assertThat(plantList[0].numberTracks, equalTo(3))
        MatcherAssert.assertThat(plantList[1].numberTracks, equalTo(4))
    }
}