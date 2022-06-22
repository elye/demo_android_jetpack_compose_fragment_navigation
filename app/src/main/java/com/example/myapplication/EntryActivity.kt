package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.myapplication.option.nofragment.NoFragmentActivity
import com.example.myapplication.option.persisted.PersistedActivity
import com.example.myapplication.option.restorable.NonRestorableActivity
import com.example.myapplication.option.restorable.RestorableActivity
import com.example.myapplication.option.stackoverflow.StackOverflowActivity
import com.example.myapplication.ui.theme.MyApplicationTheme

class EntryActivity: ComponentActivity() {

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

    @Composable
    fun MainScreen() {
        Column {
            Button(onClick = {
                startActivity(Intent(
                    this@EntryActivity,
                    NoFragmentActivity::class.java)
                )
            }) {
                Text("No Fragment Normal Bottom Sheet")
            }
            Button(onClick = {
                startActivity(Intent(
                    this@EntryActivity,
                    StackOverflowActivity::class.java)
                )
            }) {
                Text("StackOverflow Fragment in Bottom Sheet")
            }
            Button(onClick = {
                startActivity(Intent(
                    this@EntryActivity,
                    PersistedActivity::class.java)
                )
            }) {
                Text("Persisted Fragments in Bottom Sheet")
            }
            Button(onClick = {
                startActivity(Intent(
                    this@EntryActivity,
                    NonRestorableActivity::class.java)
                )
            }) {
                Text("Pop-able Fragments in Bottom Sheet")
            }
            Button(onClick = {
                startActivity(Intent(
                    this@EntryActivity,
                    RestorableActivity::class.java)
                )
            }) {
                Text("Pop and Restore-able Fragments in Bottom Sheet")
            }
        }
    }
}

