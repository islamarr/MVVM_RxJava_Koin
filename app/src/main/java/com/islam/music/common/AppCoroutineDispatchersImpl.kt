package com.islam.music.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class AppCoroutineDispatchersImpl @Inject constructor() : AppCoroutineDispatchers {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
}