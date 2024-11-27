package edu.iesam.examaad1eval.features.ex2.data.local.db

import edu.iesam.examaad1eval.features.ex2.domain.Game

class Ex2DbLocalDataSource(
    private val ex2Dao: Ex2Dao,
    private val gameMapper: GameMapper
) {

    suspend fun save(game: Game) {
        ex2Dao.save(gameMapper.toEntity(game))
    }
    suspend fun getAllUsers(): List<Game> {
        return ex2Dao.getAllUsers().map { gameMapper.toDomain(it) }
    }
}