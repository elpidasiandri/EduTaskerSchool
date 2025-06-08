package com.example.edutasker.composable.login

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.RawRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.edutasker.screens.login.viewModelState.stateAndEvents.LoginEvents
import com.example.edutasker.ui.theme.LightGray
import com.example.edutasker.ui.theme.Red
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.edutasker.R
import com.example.edutasker.ui.theme.PurpleGrey40

@Composable
fun LoginScreenComposable(
    onEvent: (LoginEvents) -> Unit,
) {
    val context = LocalContext.current
    fun loadImageFromRaw(@RawRes resId: Int): Bitmap {
        val inputStream = context.resources.openRawResource(resId)
        return BitmapFactory.decodeStream(inputStream)
    }

    var isPasswordVisible by remember { mutableStateOf(false) }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val buttonIsEnabled = email.isNotEmpty() && password.isNotEmpty()
    val loginBitmap = remember { loadImageFromRaw(R.raw.login) }
    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(colors = listOf(LightGray, PurpleGrey40)))
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    bitmap = loginBitmap.asImageBitmap(),
                    contentDescription = "Choose Image",
                    modifier = Modifier
                        .height(180.dp)
                        .width(180.dp)
                )

                OutlinedTextField(
                    value = email,
                    onValueChange = { newEmail ->
                        email = newEmail
                    },
                    label = { Text(stringResource(R.string.email), fontSize = 20.sp) },
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                    modifier = Modifier
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (buttonIsEnabled) {
                                onEvent(LoginEvents.TryToConnect(email, password))
                            }
                            keyboardController?.hide()
                        }
                    ),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledTextColor = Color.Blue,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.7f)
                    )
                )

                OutlinedTextField(
                    value = password,
                    onValueChange = { newPassword ->
                        password = newPassword
                    },
                    label = { Text(stringResource(R.string.password), fontSize = 20.sp) },
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black, fontSize = 18.sp),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (isPasswordVisible)
                            Icons.Default.Visibility
                        else
                            Icons.Default.VisibilityOff

                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                            Icon(
                                imageVector = icon,
                                contentDescription = if (isPasswordVisible) "Hide password" else "Show password",
                                tint = Color.Black
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            if (buttonIsEnabled) {
                                onEvent(LoginEvents.TryToConnect(email, password))
                            }
                            keyboardController?.hide()
                        }
                    ),
                    modifier = Modifier
                        .fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black,
                        disabledTextColor = Color.Transparent,
                        cursorColor = Color.Black,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Black.copy(alpha = 0.7f)
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    modifier = Modifier
                        .width(145.dp)
                        .height(48.dp),
                    onClick = {
                        onEvent(LoginEvents.TryToConnect(email, password))
                    },
                    enabled = buttonIsEnabled,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (buttonIsEnabled) Color.Blue else White,
                        contentColor = if (buttonIsEnabled) Red else Color.DarkGray
                    )
                ) {
                    Text(
                        stringResource(R.string.sign_in),
                        color = White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Preview()
@Composable
fun LoginScreenComposablePreview() {
    LoginScreenComposable({})
}

