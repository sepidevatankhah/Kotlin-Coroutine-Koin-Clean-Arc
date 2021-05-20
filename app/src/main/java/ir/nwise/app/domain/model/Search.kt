package ir.nwise.app.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResult(
    @SerializedName("results") val results: Search?
) : Parcelable

@Parcelize
data class Search(
    @SerializedName("artistmatches") val artistMatches: ArtistMatch
) : Parcelable

@Parcelize
data class ArtistMatch(
    @SerializedName("artist") val artists: List<Artist>,
) : Parcelable

@Entity(tableName = "Artist")
@Parcelize
data class Artist(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @ColumnInfo(name = "name")
    @SerializedName("name") val name: String,
    @ColumnInfo(name = "url")
    @SerializedName("url") val url: String
) : Parcelable {
    @Ignore
    @SerializedName("image")
    val images: List<ArtistImage>? = null
}

@Entity(tableName = "ArtistImage")
@Parcelize
data class ArtistImage(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @SerializedName("#text") val text: String,
    @SerializedName("size") val size: String = ""
) : Parcelable

fun List<ArtistImage>.getImage(type: ImageType): ArtistImage? {
    return this.find { it.size == type.name.toLowerCase() }
}

@Keep
enum class ImageType {
    SMALL,
    MEDIUM,
    LARGE,
    EXTRA_LARGE,
    MEGA,
    UNKNOWN;
}