package ir.nwise.app.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nwise.app.R
import ir.nwise.app.domain.model.Album
import ir.nwise.app.ui.album.TopAlbumsViewHolder
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.utils.inflate
import ir.nwise.app.ui.utils.replaceAll

internal class CachedAlbumAdapter(
    private val onItemClicked: (Album) -> Unit,
    private val onDeleteItemClicked: (Album) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<out Album>>() {

    private val items = mutableListOf<Album>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out Album> {
        return TopAlbumsViewHolder(
            containerView = parent.inflate(R.layout.item_top_album),
            onItemClicked = onItemClicked,
            onButtonAlbumClicked = onDeleteItemClicked,
            isCached = true
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out Album>, position: Int) {
        val model = items.getOrNull(position) ?: return
        when (holder) {
            is TopAlbumsViewHolder -> holder.bind(model)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(newItems: List<Album>) {
        items.replaceAll(newItems)
    }
}