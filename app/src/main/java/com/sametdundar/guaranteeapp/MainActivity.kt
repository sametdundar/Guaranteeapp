package com.sametdundar.guaranteeapp

import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.sametdundar.guaranteeapp.navigation.BottomNavigationBar
import com.sametdundar.guaranteeapp.navigation.NavGraph
import com.sametdundar.guaranteeapp.ui.theme.DarkBlue
import com.sametdundar.guaranteeapp.ui.theme.GuaranteeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts

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
                    NavGraph(
                        navController = navController,
                        paddingValues = paddingValues,
                        bottomBarState = bottomBarState
                    )
                }

                ManageStoragePermission()

            }
        }
    }
}

@Composable
fun RequestManageStoragePermission() {
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val hasManagePermission = Environment.isExternalStorageManager()

        if (!hasManagePermission) {
            // İzin yok, kullanıcıdan izin iste
            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:${context.packageName}")
            context.startActivity(intent)
        } else {
            // İzin var, buraya dosya erişimi ile ilgili işlemleri koyabilirsiniz.
        }
    } else {
        // Android 10 ve öncesi için izin iste
        // ...
    }
}

@Composable
fun ManageStoragePermission() {
    val context = LocalContext.current

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        val hasManagePermission = Environment.isExternalStorageManager()

        if (!hasManagePermission) {
            // İzin yok, kullanıcıdan izin iste
            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartActivityForResult()
            ) { result ->
                // İzin sonrası işlemler
            }

            val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
            intent.data = Uri.parse("package:${context.packageName}")
            launcher.launch(intent)

        } else {
            // İzin verildi, dış depolama işlemleri yapabilirsiniz.
        }
    }
}