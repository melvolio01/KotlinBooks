package com.example.kotlinbooks

import android.util.Log
import retrofit2.Response

class BookSearchPresenter
constructor(
    googleBooksRepository: GoogleBooksRepository,
    bookSearchContract: BookSearchContract.viewContract
) :
    BookSearchContract.presenterContract, GoogleBooksRepository.RepositoryCallback {
    val TAG = "BookSearchPresenter"

    private var repository: GoogleBooksRepository
    private var viewContract: BookSearchContract.viewContract

    init {
        this.repository = googleBooksRepository
        this.viewContract = bookSearchContract
    }

    override fun makeGoogleBooksRequest(bookName: String) {
        if (bookName.length > 0) {
            repository.searchBooks(bookName, this)
        } else viewContract.displayBookNotFoundToast()
    }

    override fun handleResponse(response: Response<RetrofitBook>) {
        if (response.isSuccessful()) {
            val bookRes: RetrofitBook = response.body()!!
            val book = bookRes.items[0]!!
            Log.i(TAG, book.volumeInfo?.title)
            if (book.volumeInfo?.title == "title") {
                viewContract.displayBookNotFoundToast()
            } else viewContract.launchBookDetailsFragment(book)
        } else {
            viewContract.displayMiscError()
        }
    }

    override fun handleError(message: String) {
        viewContract.displayErrorToast(message)
    }
}