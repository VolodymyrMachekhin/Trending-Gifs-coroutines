package com.vmac.giphy

import android.app.Application
import com.vmac.giphy.di.repositoryModule
import com.vmac.giphy.di.retrofitModule
import com.vmac.giphy.di.utilsModule
import com.vmac.giphy.ui.mainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@App)
            modules(
                utilsModule,
                retrofitModule,
                repositoryModule,
                mainModule
            )
        }
    }
}