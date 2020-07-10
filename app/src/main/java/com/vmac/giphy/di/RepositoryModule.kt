package com.vmac.giphy.di

import com.vmac.giphy.domain.GifRepository
import com.vmac.giphy.network.repository.GifDtoMapper
import com.vmac.giphy.network.repository.NetworkGifRepository
import com.vmac.giphy.network.repository.TrendingGifDtoMapper
import org.koin.dsl.module

val repositoryModule = module {

    factory { GifDtoMapper() }
    factory { TrendingGifDtoMapper(gifDtoMapper = get()) }
    factory<GifRepository> {
        NetworkGifRepository(
            client = get(),
            mapper = get(),
            dispatchersProvider = get()
        )
    }
}