package edu.iesam.examaad1eval

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.examaad1eval.features.ex2.data.Ex2DataRepository
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
        val localDataSource = Ex2DbLocalDataSource()
        val remoteDataSource = MockEx2RemoteDataSource()
        val ex2DataRepository = Ex2DataRepository(localDataSource, remoteDataSource)
        GlobalScope.launch {
            val games = ex2DataRepository.getGames()
            games.forEach {
                Log.d("@dev", "Game: ${it}")
            }
        }
    }
}