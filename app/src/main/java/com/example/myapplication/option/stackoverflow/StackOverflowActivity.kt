package com.example.myapplication.option.stackoverflow

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.SparseArray
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.BottomNavigationBar
import com.example.myapplication.TopBar
import com.example.myapplication.option.restorable.RestorableActivity.Companion.SAVED_STATE_CONTAINER_KEY
import com.example.myapplication.option.restorable.RestorableActivity.Companion.SAVED_STATE_CURRENT_TAB_KEY
import com.example.myapplication.ui.theme.MyApplicationTheme


class StackOverflowActivity : FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
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
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    fun MainScreen() {
        val navController = rememberNavController()
        BottomNavigationBar(navController)
        Scaffold(
            topBar = { TopBar() },
            bottomBar = { BottomNavigationBar(navController) }
        ) { Navigation(navController, supportFragmentManager, ::getCommitFunction) }
    }

    private fun getCommitFunction(
        fragment : Fragment,
        tag: String
    ): FragmentTransaction.(containerId: Int) -> Unit =
        {
            replace(it, fragment, tag)
        }
}


