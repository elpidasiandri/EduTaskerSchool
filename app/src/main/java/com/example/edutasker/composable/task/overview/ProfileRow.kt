package com.example.edutasker.composable.task.overview

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import coil.compose.AsyncImage
import com.example.edutasker.R
import com.example.edutasker.mockData.CurrentUser
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.graphics.Color

@Composable
fun ProfileRow(label: String, name: String, imageUrl: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = imageUrl.ifBlank { if (CurrentUser.getCurrentUserIfIsStudent()) R.raw.studentavatar else R.raw.professoravatar },
            contentDescription = null,
            modifier = Modifier
                .padding(top = 16.dp)
                .size(32.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(label, fontSize = 12.sp, color = Color.Gray)
            Text(name, fontWeight = FontWeight.Medium)
        }
    }
}
