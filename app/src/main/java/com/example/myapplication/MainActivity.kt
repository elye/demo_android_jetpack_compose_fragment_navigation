package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.util.SparseArray
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme


class MainActivity : FragmentActivity() {
    private var savedStateSparseArray = SparseArray<Fragment.SavedState>()
    private var currentSelectItemId = 0

    companion object {
        const val SAVED_STATE_CONTAINER_KEY = "ContainerKey"
        const val SAVED_STATE_CURRENT_TAB_KEY = "CurrentTabKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            savedStateSparseArray = savedInstanceState.getSparseParcelableArray(SAVED_STATE_CONTAINER_KEY)
                ?: savedStateSparseArray
            currentSelectItemId = savedInstanceState.getInt(SAVED_STATE_CURRENT_TAB_KEY)
        }
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen(supportFragmentManager)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSparseParcelableArray(SAVED_STATE_CONTAINER_KEY, savedStateSparseArray)
        outState.putInt(SAVED_STATE_CURRENT_TAB_KEY, currentSelectItemId)
    }

    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach { fragment ->
            if (fragment != null && fragment.isVisible) {
                with(fragment.childFragmentManager) {
                    if (backStackEntryCount > 0) {
                        popBackStack()
                        return
                    }
                }
            }
        }
        super.onBackPressed()
    }

    @Composable
    fun MainScreen(supportFragmentManager: FragmentManager) {
        val navController = rememberNavController()
        BottomNavigationBar(navController)
        Text("Testing")
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(navController) }
        ) { padding ->
            Navigation(navController, supportFragmentManager, padding)
        }
    }

    @Composable
    fun Navigation(navController: NavHostController,
                   supportFragmentManager: FragmentManager,
                   padding: PaddingValues) {

        NavHost(navController, startDestination = NavigationItem.Home.route) {
            composable(NavigationItem.Home.route) {
//            HomeScreen()
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {
                        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
                        if (currentFragment != null) {
                            savedStateSparseArray.put(currentSelectItemId,
                                supportFragmentManager.saveFragmentInstanceState(currentFragment)
                            )
                        }
                        currentSelectItemId = it
                        val fragment = ContainerFragment.newInstance("Home", "#FFAAAA")
                        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
                        replace(it, fragment, NavigationItem.Home.route)
                    }
                )
            }
            composable(NavigationItem.Music.route) {
//            MusicScreen()
                Log.d("Track", "Hello 1")
//            AndroidViewBinding(FragmentContainerBinding::inflate)
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {
                        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
                        if (currentFragment != null) {
                            savedStateSparseArray.put(currentSelectItemId,
                                supportFragmentManager.saveFragmentInstanceState(currentFragment)
                            )
                        }
                        currentSelectItemId = it
                        val fragment = ContainerFragment.newInstance("Music", "#FFFF00")
                        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
                        replace(it, fragment, NavigationItem.Music.route)
                    }
                )
            }
            composable(NavigationItem.Movies.route) {
//            MoviesScreen()
//            Log.d("Track", "Hello 2")
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {
                        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
                        if (currentFragment != null) {
                            savedStateSparseArray.put(currentSelectItemId,
                                supportFragmentManager.saveFragmentInstanceState(currentFragment)
                            )
                        }
                        currentSelectItemId = it
                        val fragment = ContainerFragment.newInstance("Movies", "#FF00FF")
                        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
                        replace(it, fragment, NavigationItem.Movies.route)
                    }
                )
            }
            composable(NavigationItem.Books.route) {
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {
                        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
                        if (currentFragment != null) {
                            savedStateSparseArray.put(currentSelectItemId,
                                supportFragmentManager.saveFragmentInstanceState(currentFragment)
                            )
                        }
                        currentSelectItemId = it
                        val fragment = ContainerFragment.newInstance("Books", "#00FFFF")
                        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
                        replace(it, fragment, NavigationItem.Books.route)
                    }
                )
            }
            composable(NavigationItem.Profile.route) {
//            ProfileScreen()
                FragmentContainer(
                    modifier = Modifier.fillMaxSize(),
                    fragmentManager = supportFragmentManager,
                    commit = {
                        val currentFragment = supportFragmentManager.findFragmentById(currentSelectItemId)
                        if (currentFragment != null) {
                            savedStateSparseArray.put(currentSelectItemId,
                                supportFragmentManager.saveFragmentInstanceState(currentFragment)
                            )
                        }
                        currentSelectItemId = it
                        val fragment = ContainerFragment.newInstance("Profile", "#AAAAFF")
                        fragment.setInitialSavedState(savedStateSparseArray[currentSelectItemId])
                        replace(it, fragment, NavigationItem.Profile.route)
                    }
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainScreen(supportFragmentManager)
}
