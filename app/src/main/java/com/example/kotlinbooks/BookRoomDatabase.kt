package com.example.kotlinbooks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteQuery
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = arrayOf(RoomBook::class), version = 1, exportSchema = false)
public abstract class BookRoomDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao

    private class BookDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {database ->
                scope.launch {
                    populateDatabase(database.bookDao())
                }
            }
        }

        suspend fun populateDatabase(bookDao: BookDao) {
            bookDao.deleteAll()
            // Sample books
            var book = RoomBook("1984", "George Orwell", "Arcturus Publishing",
                "2014-01-04", 317, 4.0, 5,
                "http://books.google.com/books/content?id=uyr8BAAAQBAJ&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "http://books.google.com/books/content?id=uyr8BAAAQBAJ&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                "George Orwell's dystopian masterpiece, Nineteen Eighty-Four is perhaps the most pervasively influential book of the twentieth century, making famous Big Brother, newspeak and Room 101. 'Who controls the past controls the future: who controls the present controls the past'")
            bookDao.insert(book)
            book = RoomBook("Scoop!", "Evelyn Waugh", "Back Bay Books", "1977-11-30",
                336, 0.0, 0,"http://books.google.com/books/content?id=dK6Q-Mf9xVIC&printsec=frontcover&img=1&zoom=1&source=gbs_api",
                "http://books.google.com/books/content?id=dK6Q-Mf9xVIC&printsec=frontcover&img=1&zoom=5&source=gbs_api",
                "In \\\"Scoop, \\\" surreptitiously dubbed \\\"a newspaper adventure, \\\" Waugh flays Fleet Street and the social pastimes of its war correspondants as he tells how William Boot became the star of British super-journalism an how, leaving part of his shirt in the claws of the lovely Katchen, he returned from Ishmaelia to London as the \\\"Daily's Beast's\\\" more accoladed overseas reporter.\",\n"
                )
            bookDao.insert(book)
            book = RoomBook("Psmith, Journalist", "P.G. Wodehouse", "The Floating Press", "2012-03-01", 227, 3.5, 8,
                "http://books.google.com/books/content?id=IetM99NMsPEC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api",
                "http://books.google.com/books/content?id=IetM99NMsPEC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api",
                "The globetrotting adventures and hilarious hijinks of bon vivant and force of nature Psmith continue in Psmith, Journalist. Psmith and his best friend, a cricket player by the name of Mike Jackson, travel to New York for a series of cricket matches. In his usual manner, Psmith soon finds himself sucked into several extraordinary situations, including a criminal enterprise spawned by several seedy underworld impresarios and a top role in the New York publishing industry.")
            bookDao.insert(book)
        }
    }

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
                    .addCallback(BookDatabaseCallback(scope))
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}