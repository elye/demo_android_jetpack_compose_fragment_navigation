package com.example.myapplication.option.viewbinding

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.myapplication.*
import com.example.myapplication.databinding.FragmentContainerBooksBinding
import com.example.myapplication.databinding.FragmentContainerHomeBinding
import com.example.myapplication.databinding.FragmentContainerMusicBinding
import com.example.myapplication.databinding.FragmentContainerProfileBinding

@Composable
fun Navigation(
    navController: NavHostController
) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            AndroidViewBinding(FragmentContainerHomeBinding::inflate)
        }
        composable(NavigationItem.Music.route) {
            AndroidViewBinding(FragmentContainerMusicBinding::inflate)
        }
        composable(NavigationItem.Movies.route) {
            AndroidViewBinding(FragmentContainerHomeBinding::inflate)
        }
        composable(NavigationItem.Books.route) {
            AndroidViewBinding(FragmentContainerBooksBinding::inflate)
        }
        composable(NavigationItem.Profile.route) {
            AndroidViewBinding(FragmentContainerProfileBinding::inflate)
        }
    }
}
