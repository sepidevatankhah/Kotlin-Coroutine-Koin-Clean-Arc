package ir.nwise.app.domain.usecase.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

interface DispatcherProvider {

    fun main(): CoroutineDispatcher = Dispatchers.Main
    fun default(): CoroutineDispatcher = Dispatchers.Default
    fun io(): CoroutineDispatcher = Dispatchers.IO
    fun job() : Job = Job()
    fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

}

class DefaultDispatcherProvider : DispatcherProvider