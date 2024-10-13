package com.sametdundar.guaranteeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
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
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun ListScreen(viewModel: FormViewModel = hiltViewModel(),navController: NavHostController) {

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
                        "GARANTİ BİLGİLERİNİ EKLEDİĞİNİZ ÜRÜNLERİN LİSTESİ",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 32.dp, start = 20.dp, end = 20.dp),
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkBlue
                    )
                }

                // Boşluk ekleme
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Kullanıcı Listesi
                items(userList) { user ->
                    UserItem(user = User(user.name,10),navController)
                }
            }
        }
    }
}

@Composable
fun UserItem(user: User,navController: NavHostController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = {
            navController.navigate("userDetails/${user.name}/${user.age}")
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = user.name,
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Age: ${user.age}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }
        }
    }
}
data class User(
    val name: String,
    val age: Int
)
