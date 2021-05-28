package ir.nwise.app.data.source.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.nwise.app.domain.model.ArtistImage

class ArtistImageConverter {

    @TypeConverter
    fun fromString(value: String): List<ArtistImage> {
        val listType = object : TypeToken<List<ArtistImage>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayListToString(list: List<ArtistImage>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}