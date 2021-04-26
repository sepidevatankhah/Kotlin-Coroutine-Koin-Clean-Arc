package ir.nwise.app.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder<T>(
    itemView: View
) : RecyclerView.ViewHolder(itemView),
    LayoutContainer {

    abstract fun bind(model: T)
}