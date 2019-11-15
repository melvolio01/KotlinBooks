package com.example.kotlinbooks.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlinbooks.Data.BookRoomDatabase
import com.example.kotlinbooks.Models.RoomBook
import com.example.kotlinbooks.Repositories.BookRoomRepository
import kotlinx.coroutines.launch

class BookViewModel(application: Application) : AndroidViewModel(application) {
    // viewModel maintains reference to repository to get data
    private val repository: BookRoomRepository
    val allBooks: LiveData<List<RoomBook>>

    init {
        val bookDao = BookRoomDatabase.getDatabase(application, viewModelScope).bookDao()
        repository = BookRoomRepository(bookDao)
        allBooks = repository.allBooks
    }

    // DB insert implementation hidden from the UI
    fun insert(book: RoomBook) = viewModelScope.launch {
        repository.insert(book)
    }

    fun deleteById(id: String) = viewModelScope.launch {
        repository.deleteById(id)
    }
}