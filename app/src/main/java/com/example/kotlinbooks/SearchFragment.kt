package com.example.kotlinbooks


import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_search.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A simple [Fragment] subclass.
 */
class SearchFragment : Fragment(), BookSearchContract.viewContract {
    lateinit var authorText: EditText
    lateinit var titleText: EditText
    lateinit var googleBooksRepository: GoogleBooksRepository
    lateinit var bookSearchPresenter: BookSearchPresenter

    private var TAG = "SearchFragment"
    private lateinit var bookFinderAPI: BookFinderAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        titleText = rootView.editTitle

        // Retrofit search, with logging interceptor
        val loggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("https://www.googleapis.com/books/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        bookFinderAPI = retrofit.create(BookFinderAPI::class.java)
        googleBooksRepository = GoogleBooksRepository(bookFinderAPI)
        bookSearchPresenter = BookSearchPresenter(googleBooksRepository, this)

        rootView.searchBooksBtn.setOnClickListener { searchBooks() }

        return rootView
    }

    private fun searchBooks() {
        var title = titleText.text.toString()
        if (title == "") {
            Toast.makeText(
                activity, "Please enter an author name or title",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            titleText.setText("")
            title = "=$title"
            bookSearchPresenter.makeGoogleBooksRequest(title)
        }
    }

    override fun launchBookDetailsFragment(book: BookItem) {
        var bundle = Bundle()
        bundle.putParcelable("book", book)
        view?.findNavController()?.navigate(R.id.action_searchFragment_to_bookDetail, bundle)
    }

    override fun displayMiscError() {
        Toast.makeText(activity, getString(R.string.general_error), Toast.LENGTH_SHORT).show()
    }

    override fun displayErrorToast(error: String) {
        Toast.makeText(activity, error, Toast.LENGTH_SHORT).show()
    }

    override fun displayBookNotFoundToast() {
        Toast.makeText(activity, getString(R.string.book_not_found), Toast.LENGTH_SHORT).show()
    }

    override fun displayConnectionError(message: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Request failed")
        builder.setMessage(message + ". Please check your internet connection.")
        builder.setPositiveButton(
            "Go to settings",
            DialogInterface.OnClickListener { dialogInterface, i ->
                val settingsIntent = Intent(android.provider.Settings.ACTION_SETTINGS)
                startActivityForResult(settingsIntent, 0)
            })
            .setNegativeButton("OK", null)
            .show()
    }
}
