package com.sametdundar.guaranteeapp

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.sametdundar.guaranteeapp.navigation.BottomNavigationBar
import com.sametdundar.guaranteeapp.navigation.NavGraph
import com.sametdundar.guaranteeapp.ui.theme.GuaranteeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            // Material 3 teması
            GuaranteeAppTheme {

                // BottomBar'ın görünürlük durumunu saklayan state
                val bottomBarState = rememberSaveable { (mutableStateOf(true)) }

                // Navigation Controller
                val navController = rememberNavController()

                // Material 3'ün Scaffold bileşeni
                androidx.compose.material3.Scaffold(
                    containerColor = Color.Black, // Scaffold'un arkaplan rengini ayarlıyoruz
                    bottomBar = { BottomNavigationBar(navController, bottomBarState) } // Bottom bar burada ekleniyor
                ) { paddingValues ->
                    // NavGraph ile ekranlar arası geçişi sağlıyoruz
                    NavGraph(navController = navController, paddingValues = paddingValues)
                }

                FileUploader()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuaranteeAppTheme {
        Greeting("Android")
    }
}

@Composable
fun FileUploader() {
    // Dosya seçme sonucu için bir durum oluştur
    var fileName by remember { mutableStateOf("No file selected") }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Seçilen dosyanın adını al
            fileName = it.lastPathSegment ?: "Unknown"
        }
    }

    // Arayüz
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = fileName, fontSize = 20.sp)

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { launcher.launch("*/*") }) {
            Text(text = "Select File")
        }
    }
}