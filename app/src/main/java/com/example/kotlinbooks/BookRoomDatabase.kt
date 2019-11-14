package com.example.kotlinbooks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(RoomBook::class), version = 1, exportSchema = false)
public abstract class BookRoomDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    // Singleton to prevent multiple DB instances
    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context): BookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "book_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}