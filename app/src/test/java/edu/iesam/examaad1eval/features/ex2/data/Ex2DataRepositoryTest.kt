package edu.iesam.examaad1eval.features.ex2.data

import edu.iesam.examaad1eval.features.ex2.data.local.db.Ex2DbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import edu.iesam.examaad1eval.features.ex2.domain.Game
import edu.iesam.examaad1eval.features.ex2.domain.Player
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class Ex2DataRepositoryTest {
    private val localDataSource = mockk<Ex2DbLocalDataSource>()
    private val remoteDataSource = mockk<MockEx2RemoteDataSource>()
    private lateinit var repository: Ex2DataRepository

    @BeforeEach
    fun setUp() {
        repository = Ex2DataRepository(localDataSource, remoteDataSource)
    }
    @Test
    fun testGetGamesReturnsDataFromLocalSourceWhenAvailable() = runBlocking {
        val game = Game("1", "Day of Tentacle", listOf(Player("1", "Juan")))
        coEvery { localDataSource.getAllGames() } returns Result.success(listOf(game))
        coEvery { remoteDataSource.getGames() } returns emptyList()

        val result = repository.getGames()

        assert(result.isSuccess)
        assert(result.getOrNull() == listOf(game))
    }
    @Test
    fun testGetGamesReturnsDataFromRemoteSourceWhenLocalSourceIsEmpty() = runBlocking {
        val games = listOf( Game("1", "Day of Tentacle", listOf(Player("1", "Juan"))),
                            Game("2", "Monkey Island", listOf(Player("1", "Juan"))),
                            Game("4", "Comandos 1", listOf(Player("1", "Juan"))),
                            Game("5", "Comandos 2", listOf(Player("1", "Juan"))),
                            Game("6", "Comandos 3", listOf(Player("1", "Juan"))))
        coEvery { localDataSource.getAllGames() } returns Result.failure(Exception())
        coEvery { remoteDataSource.getGames() } returns games
        coEvery { localDataSource.saveAll(games) } returns Unit

        val result = repository.getGames()

        assert(result.isSuccess)
        assert(result.getOrNull() == games)
    }
    @Test
    fun testGetGamesReturnsErrorWhenLocalAndRemoteSourcesAreEmpty() = runBlocking {
        coEvery { remoteDataSource.getGames() } returns emptyList()
        coEvery { localDataSource.getAllGames() } returns Result.failure(Exception())

        val result = repository.getGames()

        assert(result.isFailure)
    }

}