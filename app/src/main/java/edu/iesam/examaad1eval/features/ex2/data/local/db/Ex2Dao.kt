package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface Ex2Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(vararg game: GameEntity)

    @Query("SELECT * FROM $NAME_TABLE_GAME")
    suspend fun getAllUsers(): List<GameEntity>
}