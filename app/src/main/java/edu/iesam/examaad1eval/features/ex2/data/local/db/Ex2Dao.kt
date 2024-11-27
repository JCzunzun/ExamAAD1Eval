package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Ex2Dao {
    @Insert
    suspend fun save(game: GameEntity)

    @Query("SELECT * FROM $NAME_TABLE_GAME")
    suspend fun getAllUsers(): List<GameEntity>
}