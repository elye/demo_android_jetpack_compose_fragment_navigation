package com.example.myapplication.option.viewbinding

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.NavigationItem
import com.example.myapplication.R
import com.example.myapplication.fragments.ChildFragment
import com.example.myapplication.fragments.ContainerFragment.Companion.COLOR

abstract class ContainerBaseFragment : Fragment() {

    abstract val color: String
    abstract val title: String

    private var count = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (savedInstanceState != null) {
            count = childFragmentManager.backStackEntryCount
        }
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.text_title).text = title
        view.findViewById<View>(R.id.container).setBackgroundColor(Color.parseColor(color))


        view.findViewById<View>(R.id.button_open_child_fragment).setOnClickListener {
            val childKey = title + (count + 1).toString()
            childFragmentManager.beginTransaction()
                .replace(R.id.container_fragment, ChildFragment.newInstance(childKey), childKey)
                .addToBackStack(childKey)
                .commit()
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

class HomeContainerFragment: ContainerBaseFragment() {
    override val color: String = NavigationItem.Home.color
    override val title: String = NavigationItem.Home.title
}

class MusicContainerFragment: ContainerBaseFragment() {
    override val color: String = NavigationItem.Music.color
    override val title: String = NavigationItem.Music.title
}

class MoviesContainerFragment: ContainerBaseFragment() {
    override val color: String = NavigationItem.Movies.color
    override val title: String = NavigationItem.Movies.title
}

class BooksContainerFragment: ContainerBaseFragment() {
    override val color: String = NavigationItem.Books.color
    override val title: String = NavigationItem.Books.title
}

class ProfileContainerFragment: ContainerBaseFragment() {
    override val color: String = NavigationItem.Profile.color
    override val title: String = NavigationItem.Profile.title
}