package edu.iesam.examaad1eval.app.data.local.db.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import edu.iesam.examaad1eval.features.ex2.domain.Player

val gson = Gson()
class Converters {
    
    @TypeConverter
    fun to(players:String): List<Player> {
        return gson.fromJson(players, Array<Player>::class.java).toList()
    }
    @TypeConverter
    fun from(players: List<Player>): String {
        return gson.toJson(players)
    }
}