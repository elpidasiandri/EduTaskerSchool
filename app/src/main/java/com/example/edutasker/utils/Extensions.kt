package com.example.edutasker.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

fun Modifier.noRippleClickable(
    onClick: () -> Unit,
    interactionSource: MutableInteractionSource,
): Modifier {
    return this.clickable(
        onClick = onClick,
        indication = null,
        interactionSource = interactionSource
    )
}

fun <T> Flow<T>.catchAndHandleError(
    onError: (String, Int) -> Unit,
): Flow<T> {
    return this
        .catch { error ->
            when (error) {
                is Exception -> {
                    val errorMessage = error.localizedMessage ?: "An error occurred"
                    val errorCode = 500
                    onError(errorMessage, errorCode)
                }

                else -> {
                    val errorMessage = "Unknown error"
                    val errorCode = 1000
                    onError(errorMessage, errorCode)
                }
            }
        }
}