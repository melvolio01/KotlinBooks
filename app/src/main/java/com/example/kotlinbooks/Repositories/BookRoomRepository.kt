package com.example.kotlinbooks.Repositories

import androidx.lifecycle.LiveData
import com.example.kotlinbooks.Data.BookDao
import com.example.kotlinbooks.Models.RoomBook

class BookRoomRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<RoomBook>> = bookDao.getBooksByTitleAlphabetized()

    suspend fun insert(book: RoomBook) {
        bookDao.insert(book)
    }

    suspend fun deleteById(id: String){
        bookDao.deleteById(id)
    }
}