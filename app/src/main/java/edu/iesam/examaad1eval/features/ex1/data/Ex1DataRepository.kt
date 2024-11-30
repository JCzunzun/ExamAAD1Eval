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
    override fun getUsers(): Result<List<User>> {
        val users = localDataSource.getUsers()
        val remoteUsers = remoteDataSource.getUsers()
        users.onFailure {
            if (remoteUsers.isNotEmpty()){
                localDataSource.saveUsers(remoteUsers)
                return Result.success(remoteUsers)
            }
            else{
                return Result.failure(it)
            }
        }
        return users

    }

    override fun getItems(): Result<List<Item>> {
        val items = localDataSource.getItems()
        val remoteItems = remoteDataSource.getItems()
        items.onFailure {
            if (remoteItems.isNotEmpty()){
                localDataSource.saveItems(remoteItems)
                return Result.success(remoteItems)
            }
            else{
                return Result.failure(it)
            }
        }
        return items
    }

    override fun getServices(): Result<List<Services>> {
        val services = localDataSource.getServices()
        val remoteServices = remoteDataSource.getServices()
        services.onFailure {
            if (remoteServices.isNotEmpty()){
                localDataSource.saveServices(remoteServices)
                return Result.success(remoteServices)
            }
            else{
                return Result.failure(it)
            }
        }
        return services
    }
}