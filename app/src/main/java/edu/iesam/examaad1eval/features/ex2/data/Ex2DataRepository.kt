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

    override suspend fun getGames(): Result<List<Game>> {
        val list = mutableListOf<Game>()
        val localGames = localDataSource.getAllUsers()
        val remoteGames = remoteDataSource.getGames()
        localGames.onFailure {
                val saveList=remoteGames.slice(0..4)
                localDataSource.saveAll(saveList)
            list.addAll(remoteGames)
        }.onSuccess {
            list.addAll(it)
            list.addAll(remoteGames.filter { it !in list })
        }
        return Result.success(list.distinctBy { it.id })
    }
}