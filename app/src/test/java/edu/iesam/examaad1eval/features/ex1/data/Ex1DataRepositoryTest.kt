package edu.iesam.examaad1eval.features.ex1.data

import edu.iesam.examaad1eval.features.ex1.data.local.Ex1XmlLocalDataSource
import edu.iesam.examaad1eval.features.ex1.data.remote.MockEx1RemoteDataSource
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class Ex1DataRepositoryTest{
    private val localDataSource= mockk<Ex1XmlLocalDataSource>()
    private val remoteDataSource= mockk<MockEx1RemoteDataSource>()
    private lateinit var repository: Ex1DataRepository

    @BeforeEach
    fun setUp(){
        repository = Ex1DataRepository(localDataSource, remoteDataSource)
    }

    @Test
    fun `test getUsers return data from local data source when available`() = runBlocking {
        val users = listOf(User("1", "Juan", "Perez"))
        coEvery { localDataSource.getUsers() } returns Result.success(users)
        coEvery { remoteDataSource.getUsers() } returns emptyList()

        val result = repository.getUsers()

        assert(result.isSuccess)
        assert(result.getOrNull() == users)
    }

    @Test
    fun `test getUsers return data from remote data source when local data source is failure`() = runBlocking {
        val users = listOf(User("1", "Juan", "Perez"))
        coEvery { localDataSource.getUsers() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getUsers() } returns users
        coEvery { localDataSource.saveUsers(users) } returns Unit

        val result = repository.getUsers()

        assertNotNull(result.isSuccess)
        assertEquals(users, result.getOrNull())
    }

    @Test
    fun `test getUsers return failure when local is failure and remote data sources are empty`()= runBlocking {
        coEvery { localDataSource.getUsers() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getUsers() } returns emptyList()

        val result = repository.getUsers()

        assert(result.isFailure)
    }

    @Test
    fun `test getItems return data from local data source when available`() = runBlocking {
        val items = listOf(Item("1", "Item 1", 10.0))
        coEvery { localDataSource.getItems() } returns Result.success(items)
        coEvery { remoteDataSource.getItems() } returns emptyList()

        val result = repository.getItems()

        assert(result.isSuccess)
        assert(result.getOrNull() == items)
    }

    @Test
    fun `test getItems return data from remote data source when local data source is failure`() = runBlocking {
        val items = listOf(Item("1", "Item 1", 10.0))
        coEvery { localDataSource.getItems() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getItems() } returns items
        coEvery { localDataSource.saveItems(items) } returns Unit

        val result = repository.getItems()

        assert(result.isSuccess)
        assert(result.getOrNull() == items)

    }

    @Test
    fun `test getItems return failure when local is failure and remote data sources are empty`()= runBlocking {
        coEvery { localDataSource.getItems() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getItems() } returns emptyList()

        val result = repository.getItems()

        assert(result.isFailure)

    }

    @Test
    fun `test getServices return data from local data source when available`() = runBlocking {
        val services = listOf(Services("1", "Service 1"))
        coEvery { localDataSource.getServices() } returns Result.success(services)
        coEvery { remoteDataSource.getServices() } returns emptyList()

        val result = repository.getServices()

        assert(result.isSuccess)
        assert(result.getOrNull() == services)
    }

    @Test
    fun `test getServices return data from remote data source when local data source is failure`()= runBlocking {
        val services = listOf(Services("1", "Service 1"))
        coEvery { localDataSource.getServices() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getServices() } returns services
        coEvery { localDataSource.saveServices(services) } returns Unit

        val result = repository.getServices()

        assert(result.isSuccess)
        assert(result.getOrNull() == services)

    }

    @Test
    fun `test getServices return failure when local is failure and remote data sources are empty`()= runBlocking{
        coEvery { localDataSource.getServices() } returns Result.failure(Exception("No data"))
        coEvery { remoteDataSource.getServices() } returns emptyList()

        val result = repository.getServices()

        assert(result.isFailure)

    }
}