package com.example.edutasker.composable.task.preview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import com.example.edutasker.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileRow(label: String, name: String, imageUrl: String, isStudent: Boolean) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = imageUrl.ifBlank { if (isStudent) R.raw.studentavatar else R.raw.professoravatar },
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(34.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color.Black)
            Text(name, fontWeight = FontWeight.Medium)
        }
    }
}
