package com.vmac.giphy.ui

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.vmac.giphy.domain.GifRepository
import com.vmac.giphy.domain.logging.Logger
import com.vmac.giphy.domain.model.Gif
import com.vmac.giphy.domain.model.TrendingGifs
import com.vmac.giphy.test.commons.CoroutinesTestRule
import com.vmac.giphy.test.commons.TestDispatcherProvider
import com.vmac.giphy.test.commons.trendingGifs
import com.vmac.giphy.ui.adapter.GiphyListItem
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class TrendingViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    private val expectedError: Throwable = mock()
    private val repository: GifRepository = mock()

    private val logger: Logger = mock()

    private fun createViewModel(): TrendingViewModel {
        return TrendingViewModel(
            repository = repository,
            logger = logger,
            dispatchersProvider = TestDispatcherProvider()
        )
    }

    @Test
    fun `when init should load and display result`() {
        runBlocking {
            whenever(
                repository.getTrendingGifs(offset = 0)
            ).thenReturn(trendingGifs)
            val viewModel = createViewModel()

            verify(repository)
                .getTrendingGifs(
                    offset = 0
                )

            assertEquals(trendingGifs.items.size + 1, viewModel.giphyListItems.size)
            assertEquals(trendingGifs.items[0], viewModel.itemAt(0))
            assertEquals(GiphyListItem.LoadMore, viewModel.giphyListItems[1])
        }
    }

    @Test
    fun `when init and error should log error`() {
        runBlocking {

            doAnswer {
                throw expectedError
            }
                .whenever(repository)
                .getTrendingGifs(offset = 0)
            createViewModel()

            verify(logger).e(
                throwable = expectedError,
                message = "Error while initial loading"
            )
        }
    }

    @Test
    fun `when setup called and empty result should handle empty result case can't load more`() {
        runBlocking {
            whenever(
                repository.getTrendingGifs(offset = 0)
            ).thenReturn(
                TrendingGifs(
                    items = listOf(),
                    totalTrendingCount = 0
                )
            )
            createViewModel()

            verify(repository)
                .getTrendingGifs(
                    offset = 0
                )

            verify(logger).e(
                message = "Should never happen"
            )
        }
    }

    @Test
    fun `when loading more and items are not empty should load more`() {
        runBlocking {
            whenever(
                repository.getTrendingGifs(offset = 0)
            ).thenReturn(trendingGifs)
            whenever(
                repository.getTrendingGifs(offset = 1)
            ).thenReturn(trendingGifs)
            val viewModel = createViewModel()

            viewModel.loadMore.invoke()
            verify(repository)
                .getTrendingGifs(
                    offset = 0
                )
            verify(repository)
                .getTrendingGifs(
                    offset = 1
                )

            assertEquals(2 * trendingGifs.items.size + 1, viewModel.giphyListItems.size)
            assertEquals(trendingGifs.items[0], viewModel.itemAt(0))
            assertEquals(trendingGifs.items[0], viewModel.itemAt(1))
            assertEquals(GiphyListItem.LoadMore, viewModel.giphyListItems[2])
        }
    }

    @Test
    fun `when loading more and received empty list should stop loading`() {
        runBlocking {
            whenever(
                repository.getTrendingGifs(offset = 0)
            ).thenReturn(trendingGifs)
            whenever(
                repository.getTrendingGifs(offset = 1)
            ).thenReturn(
                TrendingGifs(
                    items = listOf(),
                    totalTrendingCount = 0
                )
            )
            val viewModel = createViewModel()


            viewModel.loadMore.invoke()
            viewModel.loadMore.invoke()
            verify(repository).getTrendingGifs(offset = 0)
            verify(repository, times(1)).getTrendingGifs(offset = 1)


            assertEquals(1, viewModel.giphyListItems.size)
            assertEquals(trendingGifs.items[0], viewModel.itemAt(0))
        }
    }

    @Test
    fun `when loading more error should log error`() {
        runBlocking {
            whenever(
                repository.getTrendingGifs(offset = 0)
            ).thenReturn(trendingGifs)
            doAnswer {
                throw expectedError
            }
                .whenever(repository)
                .getTrendingGifs(offset = 1)
            createViewModel().loadMore.invoke()
            verify(logger).e(
                throwable = expectedError,
                message = "Error while loading more"
            )
        }
    }

    private fun TrendingViewModel.itemAt(index: Int): Gif {
        return (giphyListItems[index] as GiphyListItem.DisplayGiphy).gif
    }
}