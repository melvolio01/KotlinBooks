package com.example.kotlinbooks


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // inflate the layout
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val navController = findNavController()

        rootView.searchBtn.setOnClickListener {
            if (navController.currentDestination?.id == R.id.homeFragment) {
                findNavController().navigate(R.id.action_homeFragment_to_searchFragment)
            }
        }
        rootView.viewBtn.setOnClickListener {
            if (navController.currentDestination?.id == R.id.homeFragment) {
                findNavController().navigate(R.id.action_homeFragment_to_viewLibraryFragment)
            }
        }

        // Inflate the layout for this fragment
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Override backbutton to prevent unforeseen changes caused by Navigation
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {

        }
    }

}
