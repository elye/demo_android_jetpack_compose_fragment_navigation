package com.example.myapplication

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.ContextWrapper
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TopBar() {
    val activity = LocalContext.current as Activity
    val title = activity.title?.toString() ?: "No Title"
    TopAppBar(
        title = { Text(text = title, fontSize = 18.sp) },
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = Color.White
    )
}

inline fun <reified T> Context.getActivityLabel(): String {
    val componentName = ComponentName(this, T::class.java)
    val activityInfo = packageManager.getActivityInfo(componentName, 0)
    // loadLabel takes care of cases when the label is specified as a String literal
    // as well as cases when the label is specified as a String resource
    return activityInfo.loadLabel(packageManager).toString()
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview() {
    TopBar()
}
