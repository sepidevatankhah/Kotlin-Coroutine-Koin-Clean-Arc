package ir.nwise.app.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Captures all the Data emitted by [LiveData] in question until the returned lambda is invoked which in its turn
 * returns the list of all observed data.
 */
fun <T> LiveData<T>.captureEmittedData(): () -> List<T?> {
    val list = mutableListOf<T?>()
    val observer = Observer<T> {
        list.add(it)
    }
    this.observeForever(observer)
    return {
        this.removeObserver(observer)
        list
    }
}

/**
 * Captures all the Data emitted by [LiveData] in question until the returned lambda is invoked which in its turn
 * returns the latest emitted value.
 */
fun <T> LiveData<T>.captureLastEmittedValue(): () -> T? {
    val lambda = this.captureEmittedData()
    return {
        lambda.invoke().last()
    }
}

fun <T> LiveData<T>.captureFirstEmittedValue(): () -> T? {
    val lambda = this.captureEmittedData()
    return {
        lambda.invoke().first()
    }
}

