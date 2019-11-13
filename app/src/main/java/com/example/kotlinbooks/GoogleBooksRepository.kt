package com.example.kotlinbooks

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoogleBooksRepository(bookFinderAPI: BookFinderAPI) {
    private var TAG = "GoogleBooksRepository"
    var bookFinderAPI: BookFinderAPI

    init {
        this.bookFinderAPI = bookFinderAPI
    }

    fun searchBooks(
        bookTitle: String,
        callback: RepositoryCallback
    ) {
        val call: Call<RetrofitBook> =
            bookFinderAPI.getBook(bookTitle, BuildConfig.GOOGLE_BOOKS_KEY)
        call.enqueue(object : Callback<RetrofitBook> {
            override fun onFailure(call: Call<RetrofitBook>, t: Throwable) {
                val message = t.message
                callback.handleError(message!!)
            }

            override fun onResponse(call: Call<RetrofitBook>, response: Response<RetrofitBook>) {
                callback.handleResponse(response)
            }
        })
    }

    interface RepositoryCallback {
        fun handleResponse(response: Response<RetrofitBook>)

        fun handleError(message: String)
    }
}
