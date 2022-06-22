package com.example.myapplication.option.persisted

import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentContainerView
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.commit

@Composable
fun FragmentContainer(
    modifier: Modifier = Modifier,
    fragmentManager: FragmentManager,
    commit: FragmentTransaction.(containerId: Int) -> Unit
) {
    val containerId by rememberSaveable { mutableStateOf(View.generateViewId()) }
    AndroidView(
        modifier = modifier,
        factory = { context ->
            fragmentManager.findFragmentById(containerId)?.view
                ?.also { (it.parent as? ViewGroup)?.removeView(it) }
                ?: FragmentContainerView(context)
                    .apply { id = containerId }
                    .also {
                        fragmentManager.commit { commit(it.id) }
                    }
        },
        update = {}
    )
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
