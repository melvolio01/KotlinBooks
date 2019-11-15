package com.example.kotlinbooks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_library_detail.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class LibraryDetailFragment : Fragment() {
    val TAG = "LibraryDetailFragment"
    lateinit var book: RoomBook
    lateinit var title: String
    lateinit var id: String
    lateinit var leadAuthor: String
    lateinit var publisher: String
    lateinit var publishedIn: String
    var pageCount: Int = 0
    var averageRating: Double = 0.0
    var totalRatings: Int = 0
    lateinit var description: String
    lateinit var smallThumbNail: String
    lateinit var thumbNail: String

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_library_detail, container, false)
        book = arguments?.getParcelable<RoomBook>("book")!!
        populateDetailViews()

        rootView.removeBook.setOnClickListener {
            deleteBook()
        }

        return rootView
    }

    fun loadImage(imageUrl: String?) {
        Glide
            .with(this)
            .load(imageUrl)
            .into(rootView.libThumbnailView)
    }

    fun populateDetailViews() {
        thumbNail = book.thumbnail
        loadImage(thumbNail)

        id = book.id
        title = book.title
        leadAuthor = book.leadAuthor
        publisher = book.publisher
        publishedIn = book.publishedDate
        pageCount = book.pageCount
        averageRating = book.averageRating
        totalRatings = book.ratingsCount
        description = book.description
        smallThumbNail = book.smallThumbnail

        // create bookDetail string
        val bookDetail = "Author: $leadAuthor " +
                "\nPublisher: $publisher" + "\nDate of Publication: $publishedIn" +
                "\nPage Count: $pageCount" +
                "\nAverage Rating: $averageRating" +
                "\nNumber of Ratings: $totalRatings" +
                "\nDescription: $description"
        rootView.libBookDetailsView.setText(bookDetail)
        rootView.libTitleView.setText(title)
    }

    private fun deleteBook() {
        val bookViewModel: BookViewModel =
            ViewModelProvider(this).get(BookViewModel::class.java)
        bookViewModel.deleteById(id)

        CoroutineScope(Dispatchers.Main).launch {
            showToast()
            delay(1000)
            navigateToCollection()
        }
    }

    fun showToast() {
        Toast.makeText(requireContext(),
            "$title deleted successfully", Toast.LENGTH_SHORT).show()
    }

    fun navigateToCollection() {
        findNavController().navigate(R.id.action_libraryDetailFragment_to_viewLibraryFragment)
    }
}
