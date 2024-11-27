package edu.iesam.examaad1eval.features.ex1.data

import edu.iesam.examaad1eval.features.ex1.data.local.Ex1XmlLocalDataSource
import edu.iesam.examaad1eval.features.ex1.data.remote.MockEx1RemoteDataSource
import edu.iesam.examaad1eval.features.ex1.domain.Ex1Repository
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class Ex1DataRepository(
    private val localDataSource: Ex1XmlLocalDataSource,
    private val remoteDataSource: MockEx1RemoteDataSource
): Ex1Repository {
    override fun getUsers(): List<User> {
        val users = localDataSource.getUsers()
        if (users.isEmpty()) {
            val remoteUsers = remoteDataSource.getUsers()
            localDataSource.saveUsers(remoteUsers)
            return remoteUsers
        }
        return users
    }

    override fun getItems(): List<Item> {
        val items = localDataSource.getItems()
        if (items.isEmpty()) {
            val remoteItems = remoteDataSource.getItems()
            localDataSource.saveItems(remoteItems)
            return remoteItems
        }
        return items
    }

    override fun getServices(): List<Services> {
        val services = localDataSource.getServices()
        if (services.isEmpty()) {
            val remoteServices = remoteDataSource.getServices()
            localDataSource.saveServices(remoteServices)
            return remoteServices
        }
        return services
    }
}