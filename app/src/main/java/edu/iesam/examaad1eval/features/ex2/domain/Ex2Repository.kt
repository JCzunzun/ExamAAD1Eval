package edu.iesam.examaad1eval.features.ex2.domain

interface Ex2Repository {
    suspend fun getGames(): Result<List<Game>>
}