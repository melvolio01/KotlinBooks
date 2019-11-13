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
class SearchFragment : Fragment(), GoogleBooksRepository.RepositoryCallback {
    override fun handleResponse(response: Response<RetrofitBook>) {
        if (response.isSuccessful()) {
            var bookRes: RetrofitBook = response.body()!!
            val book = bookRes.items[0]
        }
    }

    override fun handleError(message: String) {
        println("ERROR " + message)
    }

    lateinit var authorText: EditText
    lateinit var titleText: EditText
    lateinit var googleBooksRepository: GoogleBooksRepository

    private var TAG = "SearchFragment"
    private lateinit var bookFinderAPI: BookFinderAPI

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_search, container, false)
        authorText = rootView.editAuthor
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

        rootView.searchBooksBtn.setOnClickListener { searchBooks() }

        return rootView
    }

    private fun searchBooks() {
        val author = authorText.text.toString()
        val title = "=" + titleText.text.toString()
        if (author == "" && title == "") {
            Toast.makeText(
                activity, "Please enter an author name or title",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            val bundle = bundleOf("author" to author, "title" to title)
            view?.findNavController()?.navigate(R.id.action_searchFragment_to_bookDetail, bundle)
            authorText.setText("")
            titleText.setText("")

            googleBooksRepository.searchBooks(title, this)
        }
    }
}
