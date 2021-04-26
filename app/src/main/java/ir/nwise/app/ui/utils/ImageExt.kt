package ir.nwise.app.ui

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import ir.nwise.app.R

fun ImageView.loadUrl(url: String?) {
    val glideRequestManager = Glide.with(context)
    glideRequestManager
        .load(url)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
        )
        .into(this)
}