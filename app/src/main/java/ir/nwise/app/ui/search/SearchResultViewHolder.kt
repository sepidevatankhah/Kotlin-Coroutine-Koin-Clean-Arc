package ir.nwise.app.ui.search

import android.view.View
import ir.nwise.app.domain.model.Artist
import ir.nwise.app.ui.base.BaseViewHolder
import kotlinx.android.synthetic.main.item_search_result.artist_name

class SearchResultViewHolder(
    override val containerView: View,
    private val onItemClicked: (Artist) -> Unit
) : BaseViewHolder<Artist>(containerView) {

    override fun bind(model: Artist) {
        with(model)
        {
            artist_name.text = name

            //TODO: Change the card's UI

            // artist_image.loadUrl(images.getImage(ImageType.MEDIUM)?.text)
        }
        with(containerView) {
            setOnClickListener { onItemClicked.invoke(model) }
        }
    }
}


