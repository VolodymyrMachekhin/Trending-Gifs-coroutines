package com.vmac.giphy.di

import com.vmac.giphy.common.utils.logging.LoggerImpl
import com.vmac.giphy.domain.coroutines.DispatchersProvider
import com.vmac.giphy.domain.coroutines.DispatchersProviderImpl
import com.vmac.giphy.domain.logging.Logger
import com.vmac.giphy.ui.image.GlideImageLoader
import com.vmac.giphy.ui.image.ImageLoader
import org.koin.dsl.module

val utilsModule = module {
    single<Logger> { LoggerImpl() }
    single<ImageLoader> { GlideImageLoader() }
    single<DispatchersProvider> { DispatchersProviderImpl() }
}