package ir.nwise.app.domain.model

import com.google.gson.annotations.SerializedName

data class BasePhoto(
    @SerializedName("totalHits") val totalHits: Int,
    @SerializedName("total") val total: Int,
    @SerializedName("hits") val photoList: List<PhotoResponse>
)