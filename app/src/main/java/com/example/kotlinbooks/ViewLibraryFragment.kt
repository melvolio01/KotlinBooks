package com.example.kotlinbooks


import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * A simple [Fragment] subclass.
 */
class ViewLibraryFragment : Fragment() {
    private val TAG = "ViewLibraryFragment"
    private lateinit var bookViewModel: BookViewModel

    lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG, "CREATEVIEW...")
        rootView = inflater.inflate(R.layout.fragment_view_library, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = BookListAdapter(requireContext())
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let {adapter.setBooks(it)}
        })
    }
}
