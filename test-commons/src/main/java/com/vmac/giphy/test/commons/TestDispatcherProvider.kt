package com.vmac.giphy.test.commons

import com.vmac.giphy.domain.coroutines.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestDispatcherProvider :DispatchersProvider {
    override val io: CoroutineContext
        get() = Dispatchers.Unconfined
    override val default: CoroutineContext
        get() = Dispatchers.Unconfined

}