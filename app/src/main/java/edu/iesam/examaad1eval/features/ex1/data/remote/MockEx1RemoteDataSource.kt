package edu.iesam.examaad1eval.features.ex1.data.remote

import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class MockEx1RemoteDataSource {

    fun getUsers(): List<User> {
        return listOf(
            User("1", "Juan", "Perez"),
            User("2", "Pepe", "Sanchez"),
            User("3", "Maria", "Lopez"),
            User("4", "Ana", "Gonzalez")
        )
    }

    fun getItems(): List<Item> {
        return listOf(
            Item("1", "Camiseta", 10.0),
            Item("2", "Pantalon", 20.0),
            Item("3", "Zapatos", 30.0),
            Item("4", "Chaqueta", 40.0)
        )
    }

    fun getServices(): List<Services> {
        return listOf(
            Services("1", "Camiseta"),
            Services("2", "Pantalon"),
            Services("3", "Zapatos"),
            Services("4", "Chaqueta")
        )
    }


}