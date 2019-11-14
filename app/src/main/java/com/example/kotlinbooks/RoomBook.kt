package com.example.kotlinbooks

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "book_table")
class RoomBook(
    @NonNull
    var title: String,

    @NonNull
    var leadAuthor: String,

    var publisher: String,

    var publishedDate: String,

    var pageCount: Int,

    var averageRating: Double,

    var ratingsCount: Int,

    var thumbnail: String,

    var smallThumbnail: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}