package ir.nwise.app.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "Photos")
@Parcelize
data class PhotoResponse(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @SerializedName("user") val userName: String = "",
    @SerializedName("largeImageURL") val largeImageUrl: String = "",
    @SerializedName("webformatURL") val webFormatUrl: String = "",
    @SerializedName("previewURL") val previewImageUrl: String = "",
    @SerializedName("userImageURL") val userImageUrl: String = "",
    @SerializedName("views") val viewNumber: String = "",
    @SerializedName("likes") val likeNumber: String = "",
    @SerializedName("tags") val tags: String = "",
    @SerializedName("downloads") val downloadNumber: String = ""
) : Parcelable