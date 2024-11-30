package edu.iesam.examaad1eval.features.ex2.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import edu.iesam.examaad1eval.features.ex2.data.local.db.converters.Converters

@Database(entities = [GameEntity::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class Ex2DataBase: RoomDatabase() {
    abstract fun ex2Dao(): Ex2Dao
}