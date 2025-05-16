package com.example.edutasker.composable.errorOrSuccessToast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun CustomToastComposable(
    isError: Boolean,
    message: String,
    modifier: Modifier = Modifier,
    position: Alignment = Alignment.BottomCenter,
    duration: Long = 2000L,
) {
    val isVisible = remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(duration)
        isVisible.value = false
    }

    if (isVisible.value) {
        Box(
            contentAlignment = position,
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(
                        if (isError) Color.Red else Color.Green,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = message,
                    color = Color.White,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
