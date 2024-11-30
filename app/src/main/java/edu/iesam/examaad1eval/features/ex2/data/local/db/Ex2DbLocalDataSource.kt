package edu.iesam.examaad1eval.features.ex2.data.local.db

import edu.iesam.examaad1eval.features.ex2.domain.Game

class Ex2DbLocalDataSource(
    private val ex2Dao: Ex2Dao
) {

    suspend fun saveAll(game :List<Game>) {
        val gamesList = game.map {
            it.toEntity()
        }
        ex2Dao.saveAll(*gamesList.toTypedArray())
    }
    suspend fun getAllUsers(): List<Game> {
        return ex2Dao.getAllUsers().map {
            it.toDomain()
        }
    }
}