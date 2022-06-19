package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class SecondFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Track", "I am onCreateView 2")
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Track", "I am onViewCreated 2")
    }

    override fun onResume() {
        super.onResume()
        Log.d("Track", "I am onResume 2")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Track", "I am onSaveInstanceState 2")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Track", "I am onPause 2")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Track", "I am onStop 2")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Track", "I am onDestroyView 2")
    }
}