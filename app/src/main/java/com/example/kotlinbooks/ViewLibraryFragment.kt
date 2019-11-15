package com.example.kotlinbooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        rootView = inflater.inflate(R.layout.fragment_view_library, container, false)

        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_viewLibraryFragment_to_homeFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bookViewModel = ViewModelProvider(this).get(BookViewModel::class.java)
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recyclerView)
        val navController: NavController = view?.findNavController()!!
        val adapter = BookListAdapter(requireContext(), navController)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        bookViewModel.allBooks.observe(viewLifecycleOwner, Observer { books ->
            books?.let { adapter.setBooks(it) }
        })
    }
}
