package com.example.kotlinbooks

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RetrofitBook(

    @SerializedName("kind")
    var kind: String? = "books",

    @SerializedName("totalItems")
    var totalItems: Int? = 100,

    @SerializedName("items")
    var items: List<BookItem?> = List(1) { BookItem() }

) : Parcelable

@Parcelize
data class BookItem (
    @SerializedName("id")
    var id: String? = "id",

    @SerializedName("volumeInfo")
    var volumeInfo: VolumeInfo? = VolumeInfo(),

    @SerializedName("imageLinks")
    var imageLinks: ImageLinks? = ImageLinks()
) : Parcelable

@Parcelize
data class ImageLinks  (
    @SerializedName("smallThumbnail")
    var smallThumbnail: String? = "smallThumbnail",
    @SerializedName("thumbnail")
    var thumbnail: String? = "thumbnail"
) : Parcelable

@Parcelize
data class VolumeInfo (
    @SerializedName("title")
    var title: String? = "title",

    @SerializedName("authors")
    var authors: Array<String?> = emptyArray(),

    @SerializedName("publisher")
    var publisher: String? = "Random Publishing House",

    @SerializedName("publishedDate")
    var publishedDate: String? = "1999",

    @SerializedName("pageCount")
    var pageCount: Int? = 340,

    @SerializedName("averageRating")
    var averageRating: Double? = 2.2,

    @SerializedName("ratingsCount")
    var ratingsCount: Int? = 47
) : Parcelable