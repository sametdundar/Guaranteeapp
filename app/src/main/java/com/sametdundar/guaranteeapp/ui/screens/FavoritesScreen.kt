package com.sametdundar.guaranteeapp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.sametdundar.guaranteeapp.ui.theme.DarkBlue

@Composable
fun FavoritesScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(
                "FAVORITES PAGE", modifier = Modifier
                    .fillMaxWidth()
                    .align(alignment = CenterHorizontally)
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 44.sp,
                fontWeight = FontWeight.Bold,
                color = DarkBlue
            )
        }
    }
}
