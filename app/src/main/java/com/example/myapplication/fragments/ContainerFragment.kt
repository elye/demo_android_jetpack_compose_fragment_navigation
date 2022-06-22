package com.example.myapplication.fragments

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R

class ContainerFragment : Fragment() {

    companion object {
        const val KEY = "FragmentKey"
        const val COLOR = "FragmentColor"
        fun newInstance(key: String, color: String): Fragment {
            val fragment = ContainerFragment()
            val argument = Bundle()
            argument.putString(KEY, key)
            argument.putString(COLOR, color)
            fragment.arguments = argument
            return fragment
        }
    }

    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            count = childFragmentManager.backStackEntryCount
        }
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val key = it.getString(KEY)
            view.findViewById<TextView>(R.id.text_title).text = key
            view.findViewById<View>(R.id.container).setBackgroundColor(Color.parseColor(it.getString(
                COLOR
            )))


            view.findViewById<View>(R.id.button_open_child_fragment).setOnClickListener {
                val childKey = key + (count + 1).toString()
                childFragmentManager.beginTransaction()
                    .replace(R.id.container_fragment, ChildFragment.newInstance(childKey), childKey)
                    .addToBackStack(childKey)
                    .commit()
            }
        }

        childFragmentManager.addOnBackStackChangedListener { count = childFragmentManager.backStackEntryCount }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Track", "ContainerFragment onSaveInstanceState")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Track", "ContainerFragment onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Track", "ContainerFragment onStop")
    }
}
