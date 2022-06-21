package com.example.myapplication

import android.content.Context
import android.util.SparseArray
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.*

//@Composable
//fun FragmentContainer(
//    modifier: Modifier = Modifier,
//    fragmentManager: FragmentManager,
//    commit: FragmentTransaction.(containerId: Int) -> Unit
//) {
//    val containerId by rememberSaveable { mutableStateOf(View.generateViewId()) }
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            fragmentManager.findFragmentById(containerId)?.view
//                ?.also { (it.parent as? ViewGroup)?.removeView(it) }
//                ?: FragmentContainerView(context)
//                    .apply { id = containerId }
//                    .also {
//                        fragmentManager.commit { commit(it.id) }
//                    }
//        },
//        update = {}
//    )
//}

@Composable
fun FragmentContainer(
    modifier: Modifier = Modifier,
    fragmentManager: FragmentManager,
    commit: FragmentTransaction.(containerId: Int) -> Unit
) {
    val localView = LocalView.current
    // Find the parent fragment, if one exists. This will let us ensure that
    // fragments inflated via a FragmentContainerView are properly nested
    // (which, in turn, allows the fragments to properly save/restore their state)
    val parentFragment = remember(localView) {
        try {
            localView.findFragment<Fragment>()
        } catch (e: IllegalStateException) {
            // findFragment throws if no parent fragment is found
            null
        }
    }
    val containerId by rememberSaveable { mutableStateOf(View.generateViewId()) }
    val container = remember { mutableStateOf<FragmentContainerView?>(null) }
    val viewBlock: (Context) -> View = remember(localView) {
        { context ->
            FragmentContainerView(context)
                .apply { id = containerId }
                .also {
                    val fragmentManager = parentFragment?.childFragmentManager
                        ?: (context as? FragmentActivity)?.supportFragmentManager
                    fragmentManager?.commit { commit(it.id) }
                    container.value = it
                }
        }
    }
    AndroidView(
        modifier = modifier,
        factory = viewBlock,
        update = {}
    )

    // Set up a DisposableEffect that will clean up fragments when the FragmentContainer is disposed
    val localContext = LocalContext.current
    DisposableEffect(localView, localContext, container) {
        onDispose {
            val fragmentManager = parentFragment?.childFragmentManager
                ?: (localContext as? FragmentActivity)?.supportFragmentManager
            // Now find the fragment inflated via the FragmentContainerView
            val existingFragment = fragmentManager?.findFragmentById(container.value?.id ?: 0)
            if (existingFragment != null && !fragmentManager.isStateSaved) {
                // If the state isn't saved, that means that some state change
                // has removed this Composable from the hierarchy
                fragmentManager.commit {
                    remove(existingFragment)
                }
            }
        }
    }
}

//@Composable
//fun FragmentContainer(
//    modifier: Modifier = Modifier,
//    fragmentManager: FragmentManager,
//    commit: FragmentTransaction.(containerId: Int) -> Unit
//) {
//    val containerId by rememberSaveable { mutableStateOf(View.generateViewId()) }
//    var initialized by rememberSaveable { mutableStateOf(false) }
//    var initialized2 by rememberSaveable { mutableStateOf(false) }
//    AndroidView(
//        modifier = modifier,
//        factory = { context ->
//            Log.d("Track", "Factory")
//            FragmentContainerView(context)
//                .apply { id = containerId }
//        },
//        update = { view ->
//            if (!initialized) {
//                fragmentManager.commit { commit(view.id) }
//                initialized = true
//                Log.d("Track", "Initialized")
//            } else if (!initialized2) {
//                fragmentManager.onContainerAvailable(view)
//                Log.d("Track", "Else Initialized")
//                initialized2 = true
//            }
//        }
//    )
//}

///** Access to package-private method in FragmentManager through reflection */
//private fun FragmentManager.onContainerAvailable(view: FragmentContainerView) {
//    val method = FragmentManager::class.java.getDeclaredMethod(
//        "onContainerAvailable",
//        FragmentContainerView::class.java
//    )
//    method.isAccessible = true
//    method.invoke(this, view)
//}
