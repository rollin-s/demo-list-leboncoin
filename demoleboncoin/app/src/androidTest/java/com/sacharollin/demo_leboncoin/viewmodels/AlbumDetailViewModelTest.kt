package com.sacharollin.demo_leboncoin.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.sacharollin.demo_leboncoin.AppDatabase
import com.sacharollin.demo_leboncoin.album.data.AlbumRepository
import com.sacharollin.demo_leboncoin.album.viewmodels.AlbumDetailViewModel
import com.sacharollin.demo_leboncoin.helper.MainCoroutineRule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.rules.RuleChain
import javax.inject.Inject

@HiltAndroidTest
class AlbumDetailViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: AlbumDetailViewModel
    private val hiltRule = HiltAndroidRule(this)
    private val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val coroutineRule = MainCoroutineRule()

    @get:Rule
    val rule: RuleChain = RuleChain
        .outerRule(hiltRule)
        .around(instantTaskExecutorRule)
        .around(coroutineRule)
    @Inject
    lateinit var albumRepository: AlbumRepository

    @Before
    fun setUp() {
        hiltRule.inject()

        val context = InstrumentationRegistry.getInstrumentation().targetContext
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        viewModel = AlbumDetailViewModel(albumRepository)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() = runTest {
        // ViewModel shouldn't have base value, it needs to be provided from the arguments
        Assert.assertNull(viewModel.albumId.value)
    }
}
