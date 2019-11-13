package com.example.kotlinbooks


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_search.view.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

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
            title = "=$title"
            titleText.setText("")
            bookSearchPresenter.makeGoogleBooksRequest(title)
        }
    }

    override fun launchBookDetailsFragment(book: RetrofitBook) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayErrorToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayErrorToast(error: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayBookNotFoundToast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun displayConnectionError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
