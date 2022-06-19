package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FirstFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Track", "I am onCreateView")
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Track", "I am onViewCreated")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Track", "I am onSaveInstanceState")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Track", "I am onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Track", "I am onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Track", "I am onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Track", "I am onDestroyView")
    }
}