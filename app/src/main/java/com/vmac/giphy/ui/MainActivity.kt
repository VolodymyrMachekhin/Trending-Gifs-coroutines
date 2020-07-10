package com.vmac.giphy.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.vmac.giphy.R
import com.vmac.giphy.ui.adapter.GiphyListAdapter
import com.vmac.giphy.ui.image.GlideImageLoader
import com.vmac.giphy.ui.image.ImageLoader
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory {
        GiphyListBindingProvider()
    }
    factory {
        GiphyListAdapter(imageLoader = get())
    }
    viewModel {
        TrendingViewModel(
            repository = get(),
            logger = get(),
            dispatchersProvider = get()
        )
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}