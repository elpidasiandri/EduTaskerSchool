package com.example.edutasker.composable.login

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import com.example.edutasker.ui.theme.LightGray
import kotlin.random.Random

@Composable
fun BubbleBackgroundComposable(
    modifier: Modifier = Modifier,
    bubbleColor: Color = LightGray,
    numberOfBubbles: Int = 20
) {
    val infiniteTransition = rememberInfiniteTransition()

    val bubbles = remember {
        List(numberOfBubbles) {
            BubbleData(
                x = Random.nextFloat(),
                y = Random.nextFloat(),
                radius = Random.nextFloat() * 80 + 20,
                speed = Random.nextFloat() * 0.0005f + 0.0002f
            )
        }
    }

    val animatedProgress = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 20000, easing = LinearEasing)
        )
    )

    Canvas(modifier = modifier.fillMaxSize()) {
        val canvasWidth = size.width
        val canvasHeight = size.height

        bubbles.forEach { bubble ->
            val yOffset = (bubble.y + animatedProgress.value * bubble.speed * 20000) % 1f
            drawCircle(
                color = bubbleColor.copy(alpha = 0.15f + Random.nextFloat() * 0.1f),
                radius = bubble.radius,
                center = Offset(bubble.x * canvasWidth, yOffset * canvasHeight)
            )
        }
    }
}

data class BubbleData(
    val x: Float,
    val y: Float,
    val radius: Float,
    val speed: Float
)
