package edu.iesam.examaad1eval.features.ex2.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

val gson = Gson()
class Converters {
    
    @TypeConverter
    fun fromAnyToJson(value: Any?): String {
        return gson.toJson(value)
    }
    @TypeConverter
    fun fromJsonToAny(value: String?): Any? {
        val type = object : TypeToken<Any>() {}.type
        return gson.fromJson(value, type)
    }
}