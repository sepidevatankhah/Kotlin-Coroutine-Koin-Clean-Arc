package ir.nwise.app.domain.usecase.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCaseResult<T>.() -> Unit

abstract class UseCase<Param : Any?, Response> {
    private var parentJob: Job = Job()
    var backgroundContext: CoroutineContext = Dispatchers.IO
    var mainContext: CoroutineContext = Dispatchers.Main

    protected abstract suspend fun executeOnBackground(param: Param?): Response

    fun execute(param: Param? = null, block: CompletionBlock<Response>) {
        unsubscribe()
        parentJob = Job()
        CoroutineScope(mainContext + parentJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground(param)
                }
                block(UseCaseResult.Success(result))
            } catch (cancellationException: CancellationException) {
                block(UseCaseResult.Error(cancellationException))
            }
        }
    }

    fun unsubscribe() {
        parentJob.apply {
            cancelChildren()
            cancel()
        }
    }

}

sealed class UseCaseResult<out T> {
    class Success<out T>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}

