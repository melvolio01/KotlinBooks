package com.example.kotlinbooks.Interfaces

import com.example.kotlinbooks.Models.BookItem
import com.example.kotlinbooks.Models.RetrofitBook
import retrofit2.Response

interface BookSearchContract {

    interface viewContract {
        fun launchBookDetailsFragment(book: BookItem)

        fun displayMiscError()

        fun displayErrorToast(error: String)

        fun displayBookNotFoundToast()

        fun displayConnectionError(message: String)
    }

    interface presenterContract {
        fun makeGoogleBooksRequest(bookName: String)

        fun handleResponse(response: Response<RetrofitBook>)

        fun handleError(error: String)
    }
}