package com.example.edutasker.composable.login

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.edutasker.R
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginEvents
import com.example.edutasker.ui.theme.LightGray
import com.example.edutasker.ui.theme.PurpleGrey40
import com.example.edutasker.utils.noRippleClickable

@Composable
fun LoginChooseUserComposable(onEvent: (LoginEvents) -> Unit) {
    val context = LocalContext.current
    val interactionSource = remember { MutableInteractionSource() }

    fun loadImageFromRaw(@RawRes resId: Int): Bitmap {
        val inputStream = context.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(inputStream)
    }

    val professorBitmap = remember { loadImageFromRaw(R.raw.professorimage) }
    val studentBitmap = remember { loadImageFromRaw(R.raw.studentimage) }
    val chooseBitmap = remember { loadImageFromRaw(R.raw.chooseimage) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(colors = listOf(LightGray, PurpleGrey40)))
            .padding(14.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.Top),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            bitmap = chooseBitmap.asImageBitmap(),
            contentDescription = "Choose Image",
        )
        Image(
            bitmap = professorBitmap.asImageBitmap(),
            contentDescription = "Professor",
            modifier = Modifier
                .height(210.dp)
                .noRippleClickable(
                    interactionSource = interactionSource,
                    onClick = {
                        onEvent(LoginEvents.GoToLogin(isStudent = false))
                    }
                )
        )
        Image(
            bitmap = studentBitmap.asImageBitmap(),
            contentDescription = "Student",
            modifier = Modifier
                .noRippleClickable(
                    interactionSource = interactionSource,
                    onClick = {
                        onEvent(LoginEvents.GoToLogin(isStudent = true))
                    }
                )
        )
    }
}

@Preview()
@Composable
fun LoginChooseUserPreview() {
    LoginChooseUserComposable({})
}