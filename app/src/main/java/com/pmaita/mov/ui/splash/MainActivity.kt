package com.pmaita.mov.ui.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.pmaita.mov.ui.theme.MovTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovTheme {
                val navController = rememberNavController()
                AppNavHost(navController = navController)
            }
        }
    }
}