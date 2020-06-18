package com.vmac.giphy.di

import com.vmac.giphy.common.utils.logging.LoggerImpl
import com.vmac.giphy.domain.coroutines.DispatchersProvider
import com.vmac.giphy.domain.coroutines.DispatchersProviderImpl
import com.vmac.giphy.domain.logging.Logger
import com.vmac.giphy.ui.image.GlideImageLoader
import com.vmac.giphy.ui.image.ImageLoader
import dagger.Binds
import dagger.Module

@Module
abstract class UtilsModule {

    @Binds
    abstract fun provideLogger(loggerImpl: LoggerImpl): Logger

    @Binds
    abstract fun provideImageLoader(glideImageLoader: GlideImageLoader): ImageLoader

    @Binds
    abstract fun provideDispatchersProvider(glideImageLoader: DispatchersProviderImpl): DispatchersProvider
}