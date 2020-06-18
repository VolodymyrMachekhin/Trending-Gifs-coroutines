package com.vmac.giphy.domain

import com.vmac.giphy.domain.model.TrendingGifs

interface GifRepository {

    suspend fun getTrendingGifs(
        offset: Int = 0,
        limit: Int = DEFAULT_LIMIT
    ): TrendingGifs

    companion object {
        private const val DEFAULT_LIMIT: Int = 25
    }
}