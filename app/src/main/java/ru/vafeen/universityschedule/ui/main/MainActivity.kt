package ru.vafeen.universityschedule.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ru.vafeen.universityschedule.ui.navigation.AppNavHost
import ru.vafeen.universityschedule.ui.theme.MainTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainTheme {
                AppNavHost(context = this)
            }
        }
    }
}

