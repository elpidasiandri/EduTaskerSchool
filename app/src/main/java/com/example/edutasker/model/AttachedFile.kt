package com.example.edutasker.model

import android.net.Uri

data class AttachedFile(
    val name: String,
    val uri: Uri,
    val mimeType: String
)
