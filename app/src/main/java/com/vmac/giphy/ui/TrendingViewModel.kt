package com.vmac.giphy.ui

import androidx.databinding.Bindable
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.viewModelScope
import com.vmac.giphy.BR
import com.vmac.giphy.common.utils.ui.BaseViewModel
import com.vmac.giphy.domain.GifRepository
import com.vmac.giphy.domain.coroutines.DispatchersProvider
import com.vmac.giphy.domain.logging.Logger
import com.vmac.giphy.domain.model.TrendingGifs
import com.vmac.giphy.ui.adapter.GiphyListItem
import com.vmac.giphy.ui.adapter.GiphyListItem.DisplayGiphy
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TrendingViewModel @Inject constructor(
    private val repository: GifRepository,
    private val logger: Logger,
    private val dispatchersProvider: DispatchersProvider
) : BaseViewModel() {

    @get:Bindable
    var progressVisible: Boolean by observable(false, BR.progressVisible)

    @get:Bindable
    var totalCountLabel: String by observable("", BR.totalCountLabel)

    private var loadingMoreFinished: Boolean = false

    private var loadingMoreJob: Job? = null

    val giphyListItems: ObservableArrayList<GiphyListItem> = ObservableArrayList()

    val loadMore: () -> Unit = {
        // We must not calculate progress item
        if (loadingMoreJob?.isActive != true) {
            loadingMoreJob = viewModelScope.launch {
                loadMore(offset = giphyListItems.size - 1)
            }
        }
    }

    init {
        viewModelScope.launch {
            initialLoad()
        }
    }

    private suspend fun initialLoad() {
        progressVisible = true

        try {
            val result = repository
                .getTrendingGifs()
                .toDisplayResult()
            if (result.items.isNotEmpty()) {
                giphyListItems.addAll(result.items)
                giphyListItems.add(GiphyListItem.LoadMore)
            } else {
                logger.e(message = "Should never happen")
                loadingMoreFinished = true
                // Show some empty list placeholder
            }
        } catch (t: Throwable) {
            logger.e(t, "Error while initial loading")
            // Do some error handling
        } finally {
            progressVisible = false
        }
    }

    private suspend fun loadMore(offset: Int) {
        if (
            giphyListItems.isEmpty() ||
            progressVisible ||
            loadingMoreFinished
        ) {
            logger.d(message = "Loading is finished or in progress")
            return
        }
        try {
            val result = repository
                .getTrendingGifs(offset = offset)
                .toDisplayResult()

            if (result.items.isEmpty()) {
                loadingMoreFinished = true
                giphyListItems.removeAt(giphyListItems.size - 1)
            } else {
                giphyListItems.addAll(giphyListItems.size - 1, result.items)
            }
        } catch (t: Throwable) {
            logger.e(t, "Error while loading more")
            // Do some error handling
        }
    }

    private suspend fun TrendingGifs.toDisplayResult(): DisplayResult {
        return withContext(dispatchersProvider.default) {
            DisplayResult(
                items = items.map(::DisplayGiphy),
                totalCount = totalTrendingCount
            )
        }
    }

    data class DisplayResult(
        val items: List<DisplayGiphy>,
        val totalCount: Int
    )
}

