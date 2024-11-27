package edu.iesam.examaad1eval.features.ex2.data.local.db

import com.google.gson.reflect.TypeToken
import edu.iesam.examaad1eval.features.ex2.data.local.db.converters.gson
import edu.iesam.examaad1eval.features.ex2.domain.Game
import edu.iesam.examaad1eval.features.ex2.domain.Player

class GameMapper {
    fun toDomain(entity: GameEntity): Game {
        return Game(
            id = entity.id,
            title = entity.title,
            player = gson.fromJson(entity.player, object : TypeToken<List<Player>>() {}.type)
        )
    }

    fun toEntity(domain: Game): GameEntity {
        return GameEntity(
            id = domain.id,
            title = domain.title,
            player = gson.toJson(domain.player)
        )
    }
}