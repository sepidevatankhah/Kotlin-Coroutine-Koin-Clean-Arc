package ir.nwise.app.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserDto(
    @SerializedName("api_key") val api_key: String = "f627e48005c248b109dd91237127a3bf",
    @SerializedName("password") val password: String = "J@didsas008",
    @SerializedName("api_sig") val api_sig: String = "d0119e01c960034648bf6b50126e2bfa",
    @SerializedName("method") val method: String = "auth.getMobileSession",
    @SerializedName("username") val username: String = "sunvatankhah",
    @SerializedName("format") val format: String = "json"
) : Parcelable

@Parcelize
data class Session(
    @SerializedName("subscriber") val subscriber: Int = 0,
    @SerializedName("name") val name: String = "",
    @SerializedName("key") val key: String = ""
) : Parcelable


@Parcelize
data class UserResponse(@SerializedName("session") val session: Session?) : Parcelable