package com.example.edutasker.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.ui.Modifier
import com.example.edutasker.R
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

 fun Int.showErrorBasedErrorCode():Int {
     return when (this) {
         500 -> R.string.no_internet_connection
         else -> R.string.something_went_wrong
     }
}

fun String?.orEmptyIfNull(): String = this ?: ""

fun Boolean?.orEmptyIfNull(): Boolean = this ?: false
