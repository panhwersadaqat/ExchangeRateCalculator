package com.example.excahngeratedemo.util

import kotlinx.coroutines.CoroutineDispatcher

/**
Util class for coroutines dispatchers
 */
interface DispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
}