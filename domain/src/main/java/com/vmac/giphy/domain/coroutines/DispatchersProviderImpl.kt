package com.vmac.giphy.domain.coroutines

import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class DispatchersProviderImpl @Inject constructor() : DispatchersProvider {

    override val io: CoroutineContext get() = Dispatchers.IO

    override val default: CoroutineContext get() = Dispatchers.Default
}