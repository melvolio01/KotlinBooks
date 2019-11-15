package com.example.kotlinbooks.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinbooks.Models.RoomBook
import kotlinx.coroutines.CoroutineScope

@Database(entities = arrayOf(RoomBook::class), version = 3, exportSchema = false)
abstract class BookRoomDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao

    // Singleton to prevent multiple DB instances
    companion object {
        @Volatile
        private var INSTANCE: BookRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): BookRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BookRoomDatabase::class.java,
                    "book_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}