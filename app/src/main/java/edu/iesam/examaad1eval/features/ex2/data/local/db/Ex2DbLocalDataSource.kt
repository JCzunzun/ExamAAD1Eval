package edu.iesam.examaad1eval.features.ex2.data.local.db

import edu.iesam.examaad1eval.app.domain.ErrorApp
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
    suspend fun getAllUsers(): Result<List<Game>> {
        return try {
            val gamesList = ex2Dao.getAllUsers().map {
                it.toDomain()
            }
            if(gamesList.isNotEmpty()){
                Result.success(gamesList)
            }else{
                Result.failure(ErrorApp.DataErrorApp)
            }
        }catch (e:Exception){
            Result.failure(ErrorApp.DataErrorApp)
        }

    }
}