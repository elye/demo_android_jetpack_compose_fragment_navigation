package com.example.myapplication

import android.os.Bundle
import android.view.View.inflate
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.databinding.FragmentContainerBinding
import com.example.myapplication.databinding.FragmentFirstBinding
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Text("Testing")
//            MyApplicationTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    MainScreen()
//                }
//            }
        }
    }
}

@Composable
fun MainScreen() {
//    TopBar()
//    val navController = rememberNavController()
//    BottomNavigationBar(navController)
    Text("Testing")
//    Scaffold(
//        topBar = { TopBar() },
//        bottomBar = { BottomNavigationBar(navController) }
//    ) {
//            padding ->
//        Column(
//            modifier = Modifier
//                .padding(100.dp, 100.dp)
//        ) {
//
//            Text("Hello")
//
//        }
//        Navigation(navController)
//    }
}

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            HomeScreen()
        }
        composable(NavigationItem.Music.route) {
            MusicScreen()
        }
        composable(NavigationItem.Movies.route) {
            MoviesScreen()
        }
        composable(NavigationItem.Books.route) {
            BooksScreen()
        }
        composable(NavigationItem.Profile.route) {
            ProfileScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}

