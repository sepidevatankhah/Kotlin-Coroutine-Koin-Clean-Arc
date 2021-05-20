package ir.nwise.app.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ir.nwise.app.domain.model.Artist

class ArtistConverter {

    @TypeConverter
    fun stringToArtist(json: String): Artist? {
        val gson = Gson()
        val type = object : TypeToken<Artist>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun artistToString(artist: Artist): String {
        val gson = Gson()
        val type = object : TypeToken<Artist>() {}.type
        return gson.toJson(artist, type)
    }
}