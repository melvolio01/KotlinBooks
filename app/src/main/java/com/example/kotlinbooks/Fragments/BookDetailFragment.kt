package com.example.kotlinbooks.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.launch
import com.bumptech.glide.Glide
import com.example.kotlinbooks.Models.BookItem
import com.example.kotlinbooks.ViewModels.BookViewModel
import com.example.kotlinbooks.R
import com.example.kotlinbooks.Models.RoomBook
import kotlinx.android.synthetic.main.fragment_book_detail.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay

class BookDetailFragment : Fragment() {
    var TAG = "BookDetailFragment"
    lateinit var thumbnailView: ImageView
    lateinit var rootView: View
    var id: String = ""
    var title: String = ""
    var leadAuthor: String = ""
    var publisher: String = ""
    var publishedIn: String = ""
    var pageCount: Int = 0
    var averageRating: Double = 0.0
    var totalRatings: Int = 0
    var description: String = ""
    var smallThumbNail: String = ""
    var thumbNail: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val book = arguments?.getParcelable<BookItem>("book")
        populateDetailViews(book)

        rootView.addBook.setOnClickListener {
            saveBook()
        }

        return rootView
    }

    fun loadImage(imageUrl: String?) {
        thumbnailView = rootView.thumbnailView
        Glide
            .with(this)
            .load(imageUrl)
            .into(thumbnailView)
    }

    fun populateDetailViews(book: BookItem?) {
        thumbNail = book?.volumeInfo?.imageLinks?.thumbnail!!
        loadImage(thumbNail)

        id = book.id.toString()
        title = book.volumeInfo?.title.toString()
        leadAuthor = book.volumeInfo?.authors?.firstOrNull().toString()
        publisher = book.volumeInfo?.publisher.toString()
        publishedIn = book.volumeInfo?.publishedDate.toString()
        pageCount = book.volumeInfo?.pageCount!!.toInt()
        averageRating = book.volumeInfo?.averageRating!!.toDouble()
        totalRatings = book.volumeInfo?.ratingsCount!!.toInt()
        description = book.volumeInfo?.description.toString()
        smallThumbNail = book.volumeInfo?.imageLinks?.smallThumbnail.toString()

        // create bookDetail string
        val bookDetail = "Author: $leadAuthor " +
                "\nPublisher: $publisher" + "\nDate of Publication: $publishedIn" +
                "\nPage Count: $pageCount" +
                "\nAverage Rating: $averageRating" +
                "\nNumber of Ratings: $totalRatings" +
                "\nDescription: $description"
        rootView.bookDetailsView.setText(bookDetail)
        rootView.titleView.setText(title)
    }

    fun saveBook() {
        val roomBook = RoomBook(
            id, title, leadAuthor, publisher, publishedIn, pageCount,
            averageRating, totalRatings, thumbNail, smallThumbNail, description
        )
        val bookViewModel: BookViewModel =
            ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.insert(roomBook)

        CoroutineScope(Main).launch {
            showToast()
            delay(1000)
            navigateToViewLibrary()
        }
    }

    fun showToast() {
        Toast.makeText(
            requireContext(), "$title " + getString(R.string.book_added),
            Toast.LENGTH_SHORT
        ).show()
    }

    fun navigateToViewLibrary() {
        findNavController().navigate(R.id.action_bookDetail_to_viewLibraryFragment)
    }
}
