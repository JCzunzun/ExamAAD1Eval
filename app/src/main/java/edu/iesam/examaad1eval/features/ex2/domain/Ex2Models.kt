package edu.iesam.examaad1eval.features.ex2.domain

data class Game(val id: String, val title: String, val player: List<Player>)
data class Player(val id: String, val name: String)
