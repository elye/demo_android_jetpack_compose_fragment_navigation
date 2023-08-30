package com.example.myapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TabHeader(navController: NavHostController) {
    var selectedTabIndex by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf(
        NavigationItem.Home,
        NavigationItem.Music,
        NavigationItem.Movies,
        NavigationItem.Books,
        NavigationItem.Profile
    )
    TabRow(
        selectedTabIndex = selectedTabIndex,
        backgroundColor = Color.Gray,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        tabs.forEach { item ->
            selectedTabIndex = tabs.indexOf(tabs.find {it.route == currentRoute })
            Tab(
                text = {
                    Column (horizontalAlignment = CenterHorizontally) {
                        Icon(painterResource(id = item.icon), contentDescription = item.title)
                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
            )
        }
    }
}