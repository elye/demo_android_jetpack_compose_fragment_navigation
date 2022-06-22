package com.example.myapplication.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ChildFragment : Fragment() {

    companion object {
        const val KEY = "FragmentKey"
        fun newInstance(key: String): Fragment {
            val fragment = ChildFragment()
            val argument = Bundle()
            argument.putString(KEY, key)
            fragment.arguments = argument
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_child, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            view.findViewById<TextView>(R.id.child_text_title).text = it.getString(KEY)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Track", "ChildFragment onSaveInstanceState")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Track", "ChildFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Track", "ChildFragment onStop")
    }
}
