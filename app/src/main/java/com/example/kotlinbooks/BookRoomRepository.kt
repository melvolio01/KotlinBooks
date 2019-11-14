package com.example.kotlinbooks

import androidx.lifecycle.LiveData

class BookRoomRepository(private val bookDao: BookDao) {
    val allBooks: LiveData<List<RoomBook>> = bookDao.getBooksByTitleAlphabetized()

    suspend fun insert(book: RoomBook) {
        bookDao.insert(book)
    }
}