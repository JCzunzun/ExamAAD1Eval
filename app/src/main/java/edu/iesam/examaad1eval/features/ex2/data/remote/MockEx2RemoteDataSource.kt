package edu.iesam.examaad1eval.features.ex2.data.remote

import edu.iesam.examaad1eval.features.ex2.domain.Game
import edu.iesam.examaad1eval.features.ex2.domain.Player

class MockEx2RemoteDataSource {

    fun getGames(): List<Game>{
        return listOf(
            Game("1", "Day of Tentacle", getPlayers()),
            Game("2", "Monkey Island", listOf( getPlayers().first())),
            Game("4", "Comandos 1", listOf(getPlayers().last())),
            Game("5", "Comandos 2", listOf(getPlayers().last())),
            Game("6", "Comandos 3", listOf(getPlayers().last())),
            Game("7", "Comandos 4", listOf(getPlayers().last())),
            Game("8", "Comandos 5", getPlayers()),
        ).shuffled()
    }

    private fun getPlayers(): List<Player>{
        return listOf(
            Player("1", "Juan"),
            Player(id = "2", name = "Pepe")
        ).shuffled()
    }


}