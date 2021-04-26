package ir.nwise.app.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes

/**
 * Replace old items with new items
 * @param items New items
 */
fun <T> MutableCollection<T>.replaceAll(items: Collection<T>) {
    clear()
    addAll(items)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}