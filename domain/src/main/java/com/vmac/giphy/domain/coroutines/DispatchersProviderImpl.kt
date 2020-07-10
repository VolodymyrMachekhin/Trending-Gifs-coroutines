package com.vmac.giphy.domain.coroutines

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class DispatchersProviderImpl : DispatchersProvider {

    override val io: CoroutineContext get() = Dispatchers.IO

    override val default: CoroutineContext get() = Dispatchers.Default
}