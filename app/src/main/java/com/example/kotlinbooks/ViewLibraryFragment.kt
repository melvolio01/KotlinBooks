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
class ViewLibraryFragment : Fragment() {
    private var TAG = "ViewLibraryFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Log.i(TAG, "CREATEVIEW...")
        return inflater.inflate(R.layout.fragment_view_library, container, false)
    }
}
