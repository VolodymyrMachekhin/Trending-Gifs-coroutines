package com.vmac.giphy.network.repository

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.vmac.giphy.network.client.GiphyClient
import com.vmac.giphy.network.trendingGifsDto
import com.vmac.giphy.test.commons.TestDispatcherProvider
import com.vmac.giphy.test.commons.trendingGifs
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.Random

class NetworkGifRepositoryTest {

    @Test
    fun `when getTrendingGifs then calls client getTrending and maps result`() {
        val offset: Int = Random().nextInt()
        val limit: Int = Random().nextInt()

        val client: GiphyClient = mock {
            onBlocking {
                getTrending(
                    api_key = "WGOCD0YLa9KGaotBWYfkoqu5qVCFYH3w",
                    offset = offset,
                    limit = limit
                )
            } doReturn trendingGifsDto
        }

        val mapper: TrendingGifDtoMapper = mock {
            on { map(trendingGifsDto) } doReturn com.vmac.giphy.test.commons.trendingGifs
        }

        runBlocking {
            val result = NetworkGifRepository(
                client = client,
                mapper = mapper,
                dispatchersProvider = TestDispatcherProvider()
            ).getTrendingGifs(
                offset = offset,
                limit = limit
            )
            assertEquals(result, trendingGifs)
        }
    }
}