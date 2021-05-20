package ir.nwise.app.ui.search

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nwise.app.R
import ir.nwise.app.domain.model.Artist
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.utils.inflate
import ir.nwise.app.ui.utils.replaceAll

internal class SearchResultAdapter(
    private val onItemClicked: (Artist) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<out Artist>>() {

    private val items = mutableListOf<Artist>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out Artist> {
        return SearchResultViewHolder(
            containerView = parent.inflate(R.layout.item_search_result),
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out Artist>, position: Int) {
        val model = items.getOrNull(position) ?: return
        when (holder) {
            is SearchResultViewHolder -> holder.bind(model)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(newItems: List<Artist>) {
        items.replaceAll(newItems)
    }
}




