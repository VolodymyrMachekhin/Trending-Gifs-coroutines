package com.vmac.giphy.domain.coroutines

import kotlin.coroutines.CoroutineContext

interface DispatchersProvider {
    val io: CoroutineContext
    val default: CoroutineContext
}