package com.example.kotlinbooks

import com.google.gson.annotations.SerializedName

public class RetrofitBook {
    @SerializedName("kind")
    var kind: String? = "books"

    @SerializedName("totalItems")
    var totalItems: Int? = 100

    @SerializedName("items")
    var items: List<BookItem?> = List(1) {BookItem()}

    inner class BookItem {
        @SerializedName("id")
        var id: String? = "id"

        @SerializedName("volumeInfo")
        var volumeInfo: VolumeInfo? = VolumeInfo()

        @SerializedName("imageLinks")
        var imageLinks: ImageLinks? = ImageLinks()
    }

    inner class ImageLinks {
        @SerializedName("smallThumbnail")
        var smallThumbnail: String? = "smallThumbnail"
        @SerializedName("thumbnail")
        var thumbnail: String? = "thumbnail"
    }

    inner class VolumeInfo {
        @SerializedName("title")
        var title: String? = "title"

        @SerializedName("authors")
        var authors: Array<String?> = emptyArray()

        @SerializedName("publisher")
        var publisher: String? = "Random Publishing House"

        @SerializedName("publishedDate")
        var publishedDate: String? = "1999"

        @SerializedName("pageCount")
        var pageCount: Int? = 340

        @SerializedName("averageRating")
        var averageRating: Double? = 2.2

        @SerializedName("ratingsCount")
        var ratingsCount: Int? = 47

    }
}