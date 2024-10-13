package com.sametdundar.guaranteeapp

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
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

        // Durum çubuğunun tam ekran desteği için
        WindowCompat.setDecorFitsSystemWindows(window, false)



        setContent {
            // Material 3 teması
            GuaranteeAppTheme {

                // Durum çubuğunun rengini ayarlama
                window.statusBarColor = DarkBlue.toArgb()

                // BottomBar'ın görünürlük durumunu saklayan state
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                // Navigation Controller
                val navController = rememberNavController()

                // Material 3'ün Scaffold bileşeni
                androidx.compose.material3.Scaffold(
                    //containerColor = Color.Black, // Scaffold'un arkaplan rengini ayarlıyoruz
                    bottomBar = {
                        BottomNavigationBar(navController, bottomBarState)
                    } // Bottom bar burada ekleniyor

                ) { paddingValues ->
                    // NavGraph ile ekranlar arası geçişi sağlıyoruz
                    NavGraph(navController = navController, paddingValues = paddingValues, bottomBarState = bottomBarState )
                }

            }
        }
    }
}