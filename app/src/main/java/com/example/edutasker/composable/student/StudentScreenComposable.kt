package com.example.edutasker.composable.student

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import com.example.edutasker.ui.theme.Crem
import com.example.edutasker.ui.theme.LightGray

@Composable
fun StudentScreenComposable() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(LightGray, Crem)))
    )
}
