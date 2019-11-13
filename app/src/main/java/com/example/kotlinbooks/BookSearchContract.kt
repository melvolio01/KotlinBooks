package com.example.kotlinbooks

import retrofit2.Response

public interface BookSearchContract {

    interface viewContract {
        fun launchBookDetailsFragment(book: RetrofitBook)

        fun displayErrorToast()

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