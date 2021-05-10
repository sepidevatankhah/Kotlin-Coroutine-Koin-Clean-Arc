package ir.nwise.app.ui.home

import android.view.View
import ir.nwise.app.domain.model.PhotoResponse
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.loadUrl
import kotlinx.android.synthetic.main.item_food.imgPreview
import kotlinx.android.synthetic.main.item_food.txtDownload
import kotlinx.android.synthetic.main.item_food.txtLikes
import kotlinx.android.synthetic.main.item_food.txtUserName
import kotlinx.android.synthetic.main.item_food.txtView

class PhotoViewHolder(
    override val containerView: View,
    private val onItemClicked: (PhotoResponse) -> Unit
) : BaseViewHolder<PhotoResponse>(containerView) {

    override fun bind(model: PhotoResponse) {
        with(model)
        {
            txtUserName.text = userName
            imgPreview.loadUrl(previewImageUrl)
            txtLikes.text = likeNumber
            txtDownload.text = downloadNumber
            txtUserName.text = userName
            txtView.text = viewNumber
        }
        with(containerView) {
            setOnClickListener { onItemClicked.invoke(model) }
        }
    }
}


