package ir.nwise.app.ui.album

import android.view.View
import ir.nwise.app.R
import ir.nwise.app.domain.model.Album
import ir.nwise.app.domain.model.ImageType
import ir.nwise.app.domain.model.getImage
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.utils.loadUrl
import kotlinx.android.synthetic.main.item_top_album.artist_image
import kotlinx.android.synthetic.main.item_top_album.artist_name
import kotlinx.android.synthetic.main.item_top_album.save_album

class TopAlbumsViewHolder(
    override val containerView: View,
    private val onItemClicked: (Album) -> Unit,
    private val onButtonAlbumClicked: ((Album) -> Unit)? = null,
    private val isCached: Boolean = false
) : BaseViewHolder<Album>(containerView) {

    override fun bind(model: Album) {
        with(model)
        {
            artist_name?.text = name
            artist_image?.loadUrl(images.getImage(ImageType.LARGE)?.text)
            with(save_album) {
                text = if (isCached) {
                    resources.getString(R.string.delete)
                } else {
                    resources.getString(R.string.save)
                }
                setOnClickListener {
                    onButtonAlbumClicked?.invoke(model)
                }
            }
        }
        with(containerView) {
            setOnClickListener { onItemClicked.invoke(model) }
        }
    }
}


