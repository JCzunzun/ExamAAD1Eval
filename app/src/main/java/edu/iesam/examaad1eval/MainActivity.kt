package edu.iesam.examaad1eval

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import edu.iesam.examaad1eval.features.ex1.data.Ex1DataRepository
import edu.iesam.examaad1eval.features.ex1.data.local.Ex1XmlLocalDataSource
import edu.iesam.examaad1eval.features.ex1.data.remote.MockEx1RemoteDataSource
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        executeExercise1()
        //executeExercise2()
    }

    private fun executeExercise1(){
        //Ejecutar el ejercicio 1 desde aquí llamando al Ex1DataRepository directamente
        val localDataSource = Ex1XmlLocalDataSource(this)
        val remoteDataSource = MockEx1RemoteDataSource()
        val repository = Ex1DataRepository(localDataSource, remoteDataSource)
        val users = repository.getUsers()
        users.onSuccess {
            it.forEach { user ->
                Log.d("@dev", "user: $user")
            }
        }
        val items = repository.getItems()
        items.onSuccess {
            it.forEach { item ->
                Log.d("@dev", "item: $item")
            }
        }
        val services = repository.getServices()
        services.onSuccess {
            it.forEach { service ->
                Log.d("@dev", "service: $service")
            }
        }

    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun executeExercise2(){
        //Ejecutar el ejercicio 2 desde aquí llamando al Ex2DataRepository directamente
        GlobalScope.launch {
            //llamar a Room
        }
    }
}