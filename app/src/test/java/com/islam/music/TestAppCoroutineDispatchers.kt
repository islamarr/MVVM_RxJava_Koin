package com.islam.music

import com.islam.music.common.AppCoroutineDispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher

@ExperimentalCoroutinesApi
class TestAppCoroutineDispatchers(private val testCoroutineDispatcher: TestDispatcher) :
    AppCoroutineDispatchers {
    override val io get() = testCoroutineDispatcher
}
