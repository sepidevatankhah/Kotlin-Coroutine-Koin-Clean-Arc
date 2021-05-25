package ir.nwise.app.domain.usecase.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

typealias CompletionBlock<T> = UseCaseResult<T>.() -> Unit

abstract class UseCase<Param : Any?, Response>(
    private val coroutineScope: CoroutineScope = MainScope(),
    private val dispatchers: DispatcherProvider = DefaultDispatcherProvider()
) {
    protected abstract suspend fun executeOnBackground(param: Param?): Response

    fun execute(param: Param? = null, block: CompletionBlock<Response>) {
        unsubscribe()
        coroutineScope.launch(dispatchers.job() + dispatchers.main()) {
            try {
                val result = withContext(dispatchers.io()) {
                    executeOnBackground(param)
                }
                block(UseCaseResult.Success(result))
            } catch (cancellationException: CancellationException) {
                block(UseCaseResult.Error(cancellationException))
            }
        }
    }

    fun unsubscribe() {
        dispatchers.job().apply {
            cancelChildren()
            cancel()
        }
    }
}

sealed class UseCaseResult<out T> {
    class Success<out T>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
}

