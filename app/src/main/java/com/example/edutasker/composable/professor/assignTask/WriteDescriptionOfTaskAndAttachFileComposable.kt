package com.example.edutasker.composable.professor.assignTask

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachFile
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.edutasker.R
import com.example.edutasker.utils.FileHelper

@Composable
fun WriteDescriptionOfTaskAndAttachFileComposable(
    isDescriptionEmpty: (String) -> Unit,
    uriInfo: (String) -> Unit,
) {
    var description by remember { mutableStateOf("") }
    val selectedFileUri = remember { mutableStateOf<Uri?>(null) }

    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        selectedFileUri.value = uri
    }
    Column {
        OutlinedTextField(
            value = description,
            onValueChange = {
                isDescriptionEmpty(it)
                description = it
            },
            label = {
                Text(
                    stringResource(R.string.description),
                    color = Color.White,
                    fontSize = 18.sp
                )
            },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 16.sp
            ),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.AttachFile,
                    contentDescription = stringResource(R.string.attach_file),
                    tint = Color.White,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable {
                            filePickerLauncher.launch("*/*")
                        }
                )
            },
            modifier = Modifier.fillMaxWidth()
        )

        selectedFileUri.value?.let { uri ->
            val fileName = stringResource(R.string.attached_file) + " " + FileHelper.getFileNameFromUri(
                LocalContext.current, uri
            )
            uriInfo(uri.toString())
            Text(
                text = fileName,
                fontSize = 10.sp,
                color = Color.White,
                modifier = Modifier.padding(top = 4.dp, bottom = 8.dp)
            )
        }
    }
}


