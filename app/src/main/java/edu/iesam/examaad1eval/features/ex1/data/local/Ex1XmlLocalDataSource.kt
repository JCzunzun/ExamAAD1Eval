package edu.iesam.examaad1eval.features.ex1.data.local

import android.app.Service
import android.content.Context
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import edu.iesam.examaad1eval.features.ex1.domain.Item
import edu.iesam.examaad1eval.features.ex1.domain.Services
import edu.iesam.examaad1eval.features.ex1.domain.User

class Ex1XmlLocalDataSource(context: Context) {
    val sharedPreferences = context.getSharedPreferences("db-exam", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    val gson = Gson()

    fun saveItems(items: List<Item>) {
        val itemsJson = gson.toJson(items)
        editor.putString("items", itemsJson).apply()
    }

    fun saveServices(services: List<Services>) {
        val servicesJson = gson.toJson(services)
        editor.putString("services", servicesJson).apply()
    }

    fun saveUsers(users: List<User>) {
        val usersJson = gson.toJson(users)
        editor.putString("users", usersJson).apply()
    }
    fun getItems(): Result<List<Item>> {
        try {
            val itemsJson = sharedPreferences.getString("items", null)
            if (itemsJson != null) {
                val items = gson.fromJson(itemsJson, Array<Item>::class.java).toList()
                return Result.success(items)
            } else {
                return Result.failure(Exception("No items found"))
            }
        }
        catch (e: Exception){
            return Result.failure(e)
        }

    }

    fun getServices(): Result<List<Services>> {
        try {
            val servicesJson = sharedPreferences.getString("services", null)
            if (servicesJson != null) {
                val services = gson.fromJson(servicesJson, Array<Services>::class.java).toList()
                return Result.success(services)
            } else {
                return Result.failure(Exception("No services found"))
            }
        }
        catch (e: Exception){
            return Result.failure(e)
        }

    }

    fun getUsers(): Result<List<User>> {
        try {
            val usersJson = sharedPreferences.getString("users", null)
            if (usersJson != null) {
                val users = gson.fromJson(usersJson, Array<User>::class.java).toList()
                return Result.success(users)
            } else {
                return Result.failure(Exception("No users found"))
            }
        }catch (e: Exception){
            return Result.failure(e)
        }

    }
}
