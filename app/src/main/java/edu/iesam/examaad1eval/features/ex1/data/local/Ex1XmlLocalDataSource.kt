package edu.iesam.examaad1eval.features.ex1.data.local

import android.app.Service
import android.content.Context
import com.google.gson.Gson
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
    fun getItems(): List<Item> {
        val itemsJson = sharedPreferences.getString("items", null)
        return if (itemsJson != null) {
            gson.fromJson(itemsJson, object : TypeToken<List<Item>>() {}.type)
        } else {
            emptyList()
        }
    }

    fun getServices(): List<Services> {
        val servicesJson = sharedPreferences.getString("services", null)
        return if (servicesJson != null) {
            gson.fromJson(servicesJson, object : TypeToken<List<Service>>() {}.type)
        } else {
            emptyList()
        }
    }

    fun getUsers(): List<User> {
        val usersJson = sharedPreferences.getString("users", null)
        return if (usersJson != null) {
            gson.fromJson(usersJson, object : TypeToken<List<User>>() {}.type)
        } else {
            emptyList()
        }
    }
}
