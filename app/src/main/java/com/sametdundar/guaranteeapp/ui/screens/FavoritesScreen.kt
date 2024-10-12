package com.sametdundar.guaranteeapp.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sametdundar.guaranteeapp.roomdatabase.FormViewModel
import com.sametdundar.guaranteeapp.ui.theme.DarkBlue
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FavoritesScreen(viewModel: FormViewModel = hiltViewModel()) {

    val userList by viewModel.allUsers.observeAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {

        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn {
                // Başlık
                item {
                    Text(
                        "LİST SCREEN",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 44.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )
                }

                // Boşluk ekleme
                item {
                    Spacer(modifier = Modifier.height(60.dp))
                }

                // Kullanıcı Listesi
                items(userList) { user ->
                    UserItem(user = User(user.name,10))
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User) {
    Text(text = "${user.name}, Age: ${user.age}", style = MaterialTheme.typography.bodyMedium)
}

data class User(
    val name: String,
    val age: Int
)
