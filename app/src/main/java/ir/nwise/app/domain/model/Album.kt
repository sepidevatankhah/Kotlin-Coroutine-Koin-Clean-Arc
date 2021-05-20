package ir.nwise.app.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TopAlbums(
    @SerializedName("topalbums") val topAlbums: TopAlbum
) : Parcelable

@Parcelize
data class TopAlbum(
    @SerializedName("album") val albums: List<Album>
) : Parcelable

@Entity(tableName = "Album")
@Parcelize
data class Album(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("artist") val artist: Artist,
    @SerializedName("image") val images: List<ArtistImage>
) : Parcelable