package edu.iesam.examaad1eval.features.ex1.domain

interface Ex1Repository {
    fun getUsers(): Result<List<User>>
    fun getItems(): Result<List<Item>>
    fun getServices(): Result<List<Services>>
}