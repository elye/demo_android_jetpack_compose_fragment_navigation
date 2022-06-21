package com.example.myapplication

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController,
               getCommitFunction: (
                   fragment : Fragment,
                   tag: String
               ) -> (FragmentTransaction.(containerId: Int) -> Unit)
) {

    NavHost(navController, startDestination = NavigationItem.Home.route) {
        composable(NavigationItem.Home.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                commit = getCommitFunction(
                    ContainerFragment.newInstance(
                        NavigationItem.Home.title,
                        NavigationItem.Home.color
                    ),
                    NavigationItem.Home.route
                )
            )
        }
        composable(NavigationItem.Music.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                commit = getCommitFunction(
                    ContainerFragment.newInstance(
                        NavigationItem.Movies.title,
                        NavigationItem.Music.color
                    ),
                    NavigationItem.Music.route
                )
            )
        }
        composable(NavigationItem.Movies.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                commit = getCommitFunction(
                    ContainerFragment.newInstance(
                        NavigationItem.Movies.title,
                        NavigationItem.Movies.color
                    ),
                    NavigationItem.Movies.route
                )
            )
        }
        composable(NavigationItem.Books.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                commit = getCommitFunction(
                    ContainerFragment.newInstance(
                        NavigationItem.Books.title,
                        NavigationItem.Books.color
                    ),
                    NavigationItem.Books.route
                )
            )
        }
        composable(NavigationItem.Profile.route) {
            FragmentContainer(
                modifier = Modifier.fillMaxSize(),
                commit = getCommitFunction(
                    ContainerFragment.newInstance(
                        NavigationItem.Profile.title,
                        NavigationItem.Profile.color
                    ),
                    NavigationItem.Profile.route
                )
            )
        }
    }
}
