package com.example.kotlinbooks


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_book_detail.view.*

/**
 * A simple [Fragment] subclass.
 */
class BookDetailFragment : Fragment() {
    var TAG = "BookDetailFragment"
    lateinit var thumbnailView: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val  book = arguments?.getParcelable<BookItem>("book")
        val title = book?.volumeInfo?.title
        val firstAuthor = book?.volumeInfo?.authors?.firstOrNull()
        val publisher = book?.volumeInfo?.publisher
        val publishedIn = book?.volumeInfo?.publishedDate
        val pageCount = book?.volumeInfo?.pageCount
        val averageRating = book?.volumeInfo?.averageRating
        val totalRatings = book?.volumeInfo?.ratingsCount
        // handle glide request
        val imageUrl = book?.volumeInfo?.imageLinks?.thumbnail
        thumbnailView = rootView.thumbnailView
        loadImage(imageUrl)

        // create bookDetail string
        val bookDetail = "Author: $firstAuthor " +
                "\nPublisher: $publisher" + "\nDate of Publication: $publishedIn" +
                "\nPage Count: $pageCount" +
                "\nAverage Rating: $averageRating" +
                "\nNumber of Ratings: $totalRatings"
        rootView.bookDetailsView.setText(bookDetail)
        rootView.titleView.setText(title)


        return rootView
    }

    fun loadImage(imageUrl: String?) {
        Log.i(TAG, imageUrl)
            Glide
                .with(this)
                .load(imageUrl)
                .into(thumbnailView)
    }


}
