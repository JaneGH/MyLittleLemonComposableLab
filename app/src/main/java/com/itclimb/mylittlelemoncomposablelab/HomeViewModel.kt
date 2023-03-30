package com.itclimb.mylittlelemoncomposablelab


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext


class HomeViewModel( context: Context) : ViewModel() {
        private val database by lazy {

            Room.databaseBuilder(
                context,
                AppDatabase::class.java, "database"
            ).build()


    }

    fun getMenuItems(): LiveData<List<MenuItemRoom>> {
        return database.menuItemDao().getAll()
    }

    fun saveMenuToDatabase(menuItemsNetwork: List<MenuItemNetwork>) {
        val menuItemsRoom = menuItemsNetwork.map { it.toMenuItemRoom() }
         database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
    }
    fun deleteAllItemsFromDatabase() {
        database.menuItemDao().deleteAll()
    }

    fun isEmptyDatabase():Boolean {
        return database.menuItemDao().isEmpty()
    }

}