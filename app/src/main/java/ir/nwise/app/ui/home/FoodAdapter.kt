package ir.nwise.app.ui.home

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.nwise.app.R
import ir.nwise.app.domain.model.PhotoResponse
import ir.nwise.app.ui.base.BaseViewHolder
import ir.nwise.app.ui.inflate
import ir.nwise.app.ui.replaceAll

internal class FoodAdapter(
    private val onItemClicked: (PhotoResponse) -> Unit
) : RecyclerView.Adapter<BaseViewHolder<out PhotoResponse>>() {

    private val items = mutableListOf<PhotoResponse>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<out PhotoResponse> {
        return FoodViewHolder(
            containerView = parent.inflate(R.layout.item_food),
            onItemClicked = onItemClicked
        )
    }

    override fun onBindViewHolder(holder: BaseViewHolder<out PhotoResponse>, position: Int) {
        val model = items.getOrNull(position) ?: return
        when (holder) {
            is FoodViewHolder -> holder.bind(model)
        }
    }

    override fun getItemCount(): Int = items.size

    fun submitItems(newItems: List<PhotoResponse>) {
        items.replaceAll(newItems)
    }
}




