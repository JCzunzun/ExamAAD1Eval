package edu.iesam.examaad1eval.features.ex2.data

import edu.iesam.examaad1eval.features.ex2.data.local.db.Ex2DbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import edu.iesam.examaad1eval.features.ex2.domain.Ex2Repository
import edu.iesam.examaad1eval.features.ex2.domain.Game
import edu.iesam.examaad1eval.features.ex2.domain.Player

class Ex2DataRepository(
    private val localDataSource: Ex2DbLocalDataSource,
    private val remoteDataSource: MockEx2RemoteDataSource
):Ex2Repository {

    override suspend fun getGames(): List<Game> {
        val localGames = localDataSource.getAllUsers()
        if(localGames.isEmpty()){
            val remoteGames = remoteDataSource.getGames()
            localDataSource.saveAll(remoteGames)
            return remoteGames
        }
        return localGames
    }
}