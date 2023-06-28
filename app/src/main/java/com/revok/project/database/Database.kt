package com.revok.project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ModelClass::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun yourDao(): DAO

    companion object {
        @Volatile
        private var INSTANCE: com.revok.project.database.Database? = null

        fun getDatabase(context: Context): com.revok.project.database.Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.revok.project.database.Database::class.java,
                    "database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
