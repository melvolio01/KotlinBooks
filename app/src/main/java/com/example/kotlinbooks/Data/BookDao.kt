package com.example.kotlinbooks.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kotlinbooks.Models.RoomBook

@Dao
interface BookDao {

    @Query("SELECT * from book_table ORDER BY title ASC")
    fun getBooksByTitleAlphabetized(): LiveData<List<RoomBook>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(book: RoomBook)

    @Query("DELETE FROM book_table")
    suspend fun deleteAll()

    @Query("DELETE FROM book_table WHERE id = :id")
    suspend fun deleteById(id: String)
}