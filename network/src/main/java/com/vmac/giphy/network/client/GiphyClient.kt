package com.vmac.giphy.network.client

import com.vmac.giphy.network.entity.TrendingGifsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GiphyClient {

    @GET("/v1/gifs/trending")
    suspend fun getTrending(
        @Query("api_key") api_key: String,
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ): TrendingGifsDto
}