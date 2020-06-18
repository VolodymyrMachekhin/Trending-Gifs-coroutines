package com.vmac.giphy.network.repository

import com.vmac.giphy.domain.GifRepository
import com.vmac.giphy.domain.coroutines.DispatchersProvider
import com.vmac.giphy.domain.model.TrendingGifs
import com.vmac.giphy.network.client.GiphyClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkGifRepository @Inject constructor(
    private val client: GiphyClient,
    private val mapper: TrendingGifDtoMapper,
    private val dispatchersProvider: DispatchersProvider
) : GifRepository {

    override suspend fun getTrendingGifs(
        offset: Int,
        limit: Int
    ): TrendingGifs {
        return withContext(dispatchersProvider.default) {
            mapper.map(
                from = withContext(dispatchersProvider.io) {
                    client.getTrending(
                        api_key = API_KEY,
                        offset = offset,
                        limit = limit
                    )
                }
            )
        }
    }

    companion object {
        private const val API_KEY: String = "WGOCD0YLa9KGaotBWYfkoqu5qVCFYH3w"
    }
}