package com.sametdundar.guaranteeapp.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.sametdundar.guaranteeapp.roomdatabase.FormViewModel
import com.sametdundar.guaranteeapp.ui.theme.DarkBlue
import kotlinx.coroutines.launch

@Composable
fun ShareScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
            .background(DarkBlue)
    ) {

        // Ana Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            Column {
                Text(
                    "SHARE SCREEN", modifier = Modifier
                        .fillMaxWidth()
                        .align(alignment = Alignment.CenterHorizontally)
                        .padding(top = 20.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )
            }

            ImagePickerApp3()

            val viewModel: FormViewModel = hiltViewModel()

            FormScreen(viewModel)

        }

    }

}

@Composable
fun ImagePickerApp3() {
    // Seçilen resimlerin URI'lerini tutan state
    var imageUris by remember { mutableStateOf<List<Uri>>(emptyList()) }

    // Büyük hali gösterilecek resmin URI'sini tutan state
    var selectedImageUri by remember { mutableStateOf<Uri?>(null) }

    // Resim seçici başlatıcı (multiple resim seçimi için)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris: List<Uri>? ->
        uris?.let {
            imageUris = it // Seçilen URI'leri günceller
        }
    }

    Column(
        modifier = Modifier
//            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
//        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Select Images")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Seçilen resimleri yatay bir listede göstermek için LazyRow kullanıyoruz
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp) // Resimler arasına boşluk ekle
        ) {
            items(imageUris) { uri ->
                Box(
                    contentAlignment = Alignment.TopEnd,
                    modifier = Modifier.size(100.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(uri),
                        contentDescription = "Selected Image",
                        modifier = Modifier
                            .size(100.dp) // Resimleri 100x100 dp boyutuna küçült
                            .padding(8.dp)
                            .clickable { selectedImageUri = uri }, // Tıklanınca resmi büyüt
                        contentScale = ContentScale.Crop
                    )
                    // Sil butonu
                    IconButton(
                        onClick = {
                            imageUris = imageUris.toMutableList().apply { remove(uri) }
                        }
                    ) {
                        Icon(
                            painter = rememberAsyncImagePainter(android.R.drawable.ic_menu_close_clear_cancel),
                            contentDescription = "Delete Image",
                            tint = Color.Red,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }

    // Eğer bir resim seçilmişse tam ekran göster
    selectedImageUri?.let { uri ->
        ImageDialog(uri) {
            selectedImageUri = null // Dialog kapatıldığında URI'yi sıfırla
        }
    }
}


@Composable
fun ImageDialog(uri: Uri, onDismiss: () -> Unit) {
    Dialog(onDismissRequest = { onDismiss() }) {
        var scale by remember { mutableStateOf(1f) }
        var offsetX by remember { mutableStateOf(0f) }
        var offsetY by remember { mutableStateOf(0f) }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .pointerInput(Unit) {
                    detectTransformGestures { _, pan, zoom, _ ->
                        scale *= zoom
                        offsetX += pan.x
                        offsetY += pan.y
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(uri),
                contentDescription = "Full Screen Image",
                modifier = Modifier
                    .graphicsLayer(
                        scaleX = maxOf(1f, scale),
                        scaleY = maxOf(1f, scale),
                        translationX = offsetX,
                        translationY = offsetY
                    )
                    .fillMaxWidth()
                    .aspectRatio(1f), // Oranı koruyarak resmin boyutunu ayarla
                contentScale = ContentScale.Fit
            )

            IconButton(
                onClick = { onDismiss() },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = rememberAsyncImagePainter(android.R.drawable.ic_menu_close_clear_cancel),
                    contentDescription = "Close",
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}

@Composable
fun FormScreen(viewModel: FormViewModel) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var phoneNumber by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }


    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Name input field
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        // Email input field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email)
        )

        // Phone number input field
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

        // Address input field
        TextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Submit button
        Button(
            onClick = {
                println("Form submitted with: $name, $email, $phoneNumber, $address")
                coroutineScope.launch {
                    viewModel.saveFormData(
                        name.text,
                        email.text,
                        phoneNumber.text,
                        address.text,
                        arrayListOf()
                    )
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FormScreenPreview() {
    val viewModel: FormViewModel = hiltViewModel()
    FormScreen(viewModel)
}