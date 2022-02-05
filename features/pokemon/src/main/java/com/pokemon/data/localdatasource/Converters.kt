package com.pokemon.data.localdatasource

import androidx.room.TypeConverter
import com.google.gson.Gson

internal class Converters {

    @TypeConverter
    fun listToJson(value: List<String>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<String>::class.java).toList()
}