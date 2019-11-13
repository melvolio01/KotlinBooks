package com.example.kotlinbooks


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 */
class BookDetailFragment : Fragment() {
    var TAG = "BookDetailFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_book_detail, container, false)
        val  book = arguments?.getParcelable<BookItem>("book")
        val title = book?.volumeInfo?.title
        return rootView
    }

}
