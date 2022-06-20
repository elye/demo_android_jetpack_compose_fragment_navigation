package com.example.myapplication

import android.os.Bundle
import android.util.Log
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
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
            HomeScreen()
        }
        composable(NavigationItem.Music.route) {
//            MusicScreen()
            Log.d("Track", "Hello 1")
//            AndroidViewBinding(FragmentContainerBinding::inflate)
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = supportFragmentManager,
                commit = { add(it, ContainerFragment.newInstance("Music", "#FFFF00")) }
            )
        }
        composable(NavigationItem.Movies.route) {
//            MoviesScreen()
//            Log.d("Track", "Hello 2")
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = supportFragmentManager,
                commit = { add(it, ContainerFragment.newInstance("Movies", "#FF00FF")) }
            )
        }
        composable(NavigationItem.Books.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                fragmentManager = supportFragmentManager,
                commit = { add(it, ContainerFragment.newInstance("Books", "#00FFFF")) }
            )
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
//    MainScreen(supportFragmentManager)
}
