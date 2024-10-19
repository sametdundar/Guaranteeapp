package com.sametdundar.guaranteeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.sametdundar.guaranteeapp.navigation.BottomNavigationBar
import com.sametdundar.guaranteeapp.navigation.NavGraph
import com.sametdundar.guaranteeapp.ui.theme.DarkBlue
import com.sametdundar.guaranteeapp.ui.theme.GuaranteeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            GuaranteeAppTheme {
                window.statusBarColor = DarkBlue.toArgb()

                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                val navController = rememberNavController()

                androidx.compose.material3.Scaffold(
                    bottomBar = {
                        BottomNavigationBar(navController, bottomBarState)
                    }

                ) { paddingValues ->
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValues,
                        bottomBarState = bottomBarState
                    )
                }
            }
        }
    }
}