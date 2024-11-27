package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import edu.iesam.examaad1eval.features.ex2.domain.Player

const val NAME_TABLE_GAME = "games"
const val ID = "id"
@Entity(tableName = NAME_TABLE_GAME)
data class GameEntity(
    @PrimaryKey @ColumnInfo(name = ID) val id: String,
    @ColumnInfo(name = "title") val title: String,
    @Embedded val player: String
)
