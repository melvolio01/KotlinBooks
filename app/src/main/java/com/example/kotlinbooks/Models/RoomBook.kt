package com.example.kotlinbooks.Models

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "book_table")
@Parcelize
class RoomBook(
    @PrimaryKey
    var id: String,

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

    var smallThumbnail: String,

    var description: String
): Parcelable