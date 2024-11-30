package edu.iesam.examaad1eval.features.ex2.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.iesam.examaad1eval.features.ex2.domain.Player

val gson = Gson()
class Converters {
    
    @TypeConverter
    fun to(players:String): List<Player> {
        val listPlayerType = object : TypeToken<List<Player>>() {}.type
        return gson.fromJson(players, listPlayerType)}
    @TypeConverter
    fun from(players: List<Player>): String {
        return gson.toJson(players, object : TypeToken<List<Player>>() {}.type)
    }
}