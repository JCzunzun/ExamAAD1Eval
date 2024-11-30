package edu.iesam.examaad1eval

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import edu.iesam.examaad1eval.features.ex2.data.Ex2DataRepository
import edu.iesam.examaad1eval.app.data.local.db.Ex2DataBase
import edu.iesam.examaad1eval.features.ex2.data.local.db.Ex2DbLocalDataSource
import edu.iesam.examaad1eval.features.ex2.data.remote.MockEx2RemoteDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeExercise1()
        executeExercise2()
    }

    private fun executeExercise1(){
        //Ejecutar el ejercicio 1 desde aquí llamando al Ex1DataRepository directamente
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun executeExercise2(){
        var midb: Ex2DataBase
        GlobalScope.launch {
            midb = Room.databaseBuilder(this@MainActivity, Ex2DataBase::class.java, "ex2")
                .fallbackToDestructiveMigration()
                .build()
            val ex2Dao = midb.ex2Dao()
            val localDataSource = Ex2DbLocalDataSource(ex2Dao)
            val remoteDataSource = MockEx2RemoteDataSource()
            val ex2DataRepository = Ex2DataRepository(localDataSource, remoteDataSource)
            val games = ex2DataRepository.getGames()
            games.onSuccess {
                it.forEach {
                    Log.d("@dev", "Game: ${it}")
                }
            }.onFailure {
                Log.d("@dev", "Error: No se devolvio nada")
            }
        }
    }
}