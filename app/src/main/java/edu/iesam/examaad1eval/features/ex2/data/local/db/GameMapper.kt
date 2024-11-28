package edu.iesam.examaad1eval.features.ex2.data.local.db

import edu.iesam.examaad1eval.features.ex2.domain.Game

    fun GameEntity.toDomain():Game = Game(
            this.id,
            this.title,
            this.player
        )


    fun Game.toEntity(): GameEntity = GameEntity(
            this.id,
            this.title,
            this.player
        )

